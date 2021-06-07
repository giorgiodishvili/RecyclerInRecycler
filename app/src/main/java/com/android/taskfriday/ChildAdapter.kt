package com.android.taskfriday

import android.text.InputType
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.ItemInputFieldBinding
import com.android.taskfriday.model.InputFieldModel


class ChildAdapter(private val children: List<InputFieldModel>) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.onBind()
    }

    inner class ViewHolder(private val binding: ItemInputFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val child = children[absoluteAdapterPosition]
            d("children", "${children[absoluteAdapterPosition]}")
            binding.root.id = child.field_id
            binding.root.hint = child.hint
            when(child.keyboard){
                "text" -> binding.root.inputType = InputType.TYPE_CLASS_TEXT
                "number" -> binding.root.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }
    }
}