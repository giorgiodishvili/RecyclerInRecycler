package com.android.taskfriday

import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.RegistrationFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {


    private lateinit var adapter: ParentAdapter
    private lateinit var binding: RegistrationFragmentBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistrationFragmentBinding.inflate(
            inflater, container, false
        )
        initRecycler()
        viewModel.init()
        observe()
        lifecycleScope.launch {
            withContext(Dispatchers.Default) {
                init()
            }
        }
        return binding.root
    }


    private fun initRecycler(){
        adapter = ParentAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun observe(){
        viewModel._fetchedFields.observe(viewLifecycleOwner,{
            adapter.setData(it)
        })
    }

    //TODO check
    private fun init(){
        binding.Register.setOnClickListener{
            val mapForBundle: MutableMap<Int,String> = mutableMapOf()
                binding.recyclerView.children.forEach { recyclerChildren ->
                    recyclerChildren.findViewById<RecyclerView>(R.id.newsRecyclerView).children.forEach { editText ->
                        if (editText is EditText)
                            if (viewModel.isRequired(editText.id)!! && (editText).text.isNullOrBlank()) {
                                Toast.makeText(
                                    requireContext(),
                                    "PLEASE INSERT ${editText.hint} FIELDS",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@setOnClickListener
                            } else {
                                mapForBundle[editText.id] = editText.text.toString()
                            }
                    }
                }
            Toast.makeText(
                requireContext(),
                "All Good",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
