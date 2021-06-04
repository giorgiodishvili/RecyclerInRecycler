package com.android.taskfriday

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.taskfriday.databinding.ItemParentRecyclerBinding

class ParentAdapter() :    RecyclerView.Adapter<ParentAdapter.ViewHolder>(){
    private val viewPool = RecyclerView.RecycledViewPool()
    private var parents: MutableList<MutableList<User>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(ItemParentRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,
            false))
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        val childLayoutManager = LinearLayoutManager(
            holder.recyclerView.context
        )
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            d("issuccerss apply","${parents.size}")
            adapter = ChildAdapter(parents[position])
            setRecycledViewPool(viewPool)
        }

    }

    inner class ViewHolder(itemView : ItemParentRecyclerBinding) : RecyclerView.ViewHolder(itemView.root){
        val recyclerView : RecyclerView = itemView.newsRecyclerView
    }

    fun setData(items :MutableList<MutableList<User>>){
        this.parents.clear()
        this.parents.addAll(items)
        d("issuccerss","${parents.size}")
        notifyDataSetChanged()
    }


}