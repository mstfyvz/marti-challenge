package com.yavuzmobile.martichallenge.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<T, in DB : ViewDataBinding> : RecyclerView.Adapter<BaseListAdapter<T, DB>.BaseViewHolder>() {

    var items: List<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: (T) -> Unit = {}

    abstract fun layoutResource(): Int

    abstract fun bindingVariableId(): Int


    open fun bind(holder: BaseViewHolder, item: T?) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  BaseViewHolder(DataBindingUtil.inflate(layoutInflater, layoutResource(), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = if (!items.isNullOrEmpty() && position < items!!.size) items!![position] else null
        holder.bind(item)
        holder.itemView.setOnClickListener {
            item?.let { onItemClick(it) }
        }
        bind(holder, item)
    }

    override fun getItemCount() = items?.size ?: 0

    inner class BaseViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T?) {
            binding.setVariable(bindingVariableId(), item)
            binding.executePendingBindings()
        }
    }
}