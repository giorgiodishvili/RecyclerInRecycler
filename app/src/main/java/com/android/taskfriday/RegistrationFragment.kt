package com.android.taskfriday

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.taskfriday.databinding.RegistrationFragmentBinding

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
            Log.d("isSuccesfful", "${it[0]}")
            adapter.setData(it)
        })
    }

    fun init(){
        binding.Register.setOnClickListener{

        }
    }

}