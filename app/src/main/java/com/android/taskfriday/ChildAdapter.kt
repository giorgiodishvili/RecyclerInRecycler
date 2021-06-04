package com.android.taskfriday

import android.R
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.ItemInputFieldBinding


class ChildAdapter(private val children: List<User>)
    : RecyclerView.Adapter<ChildAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
       return ViewHolder(
            ItemInputFieldBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return children.size
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        holder.onBind()
    }


    inner class ViewHolder(private val binding : ItemInputFieldBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(){
            val child = children[absoluteAdapterPosition]
            d("children","${children[absoluteAdapterPosition]}")
            val childEd = EditText(binding.root.context)
            childEd.hint = child.hint
            childEd.id = child.field_id
            binding.root.addView(childEd)
        }
    }
}