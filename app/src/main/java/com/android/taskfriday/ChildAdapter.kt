package com.android.taskfriday

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.ItemInputFieldBinding
import com.android.taskfriday.model.InputFieldModel


class ChildAdapter(private val children: List<InputFieldModel>) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    companion object {

        val editTextMap: MutableMap<Int, String> = mutableMapOf()
    }


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
            if (editTextMap[child.field_id] == null) {
                editTextMap[child.field_id] = ""
                binding.root.id = child.field_id
                binding.root.hint = child.hint
            } else {
                binding.root.setText(editTextMap[child.field_id])
                binding.root.hint = child.hint
                binding.root.id = child.field_id
            }
            when (child.keyboard) {
                "text" -> binding.root.inputType = InputType.TYPE_CLASS_TEXT
                "number" -> binding.root.inputType = InputType.TYPE_CLASS_NUMBER
            }

            binding.root.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    editTextMap[child.field_id] = s.toString()
                }

            })
        }
    }
}