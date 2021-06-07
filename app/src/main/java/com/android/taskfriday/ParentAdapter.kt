package com.android.taskfriday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.ItemParentRecyclerBinding
import com.android.taskfriday.model.InputFieldModel

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    private var parents: MutableList<MutableList<InputFieldModel>> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemParentRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: ItemParentRecyclerBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val recyclerView: RecyclerView = itemView.newsRecyclerView
        fun onBind() {
            val childLayoutManager = LinearLayoutManager(
                recyclerView.context
            )
            childLayoutManager.initialPrefetchItemCount = 4
            recyclerView.apply {
                layoutManager = childLayoutManager
                adapter = ChildAdapter(parents[absoluteAdapterPosition])
                setRecycledViewPool(viewPool)
            }
        }

    }

    fun setData(items: MutableList<MutableList<InputFieldModel>>) {
        this.parents.clear()
        this.parents.addAll(items)
        notifyDataSetChanged()
    }


}