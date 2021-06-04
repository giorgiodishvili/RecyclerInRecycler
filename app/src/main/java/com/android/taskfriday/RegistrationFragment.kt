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
    private var size: Int =0

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
            size = it.size
            adapter.setData(it)
        })
    }

    //TODO check
    fun init(){
        binding.Register.setOnClickListener{
            for(i in 0..size){
                val findViewHolderForLayoutPosition =
                    binding.recyclerView.findViewHolderForLayoutPosition(i)
//                findViewHolderForLayoutPosition.itemView.
            }
        }
    }

}