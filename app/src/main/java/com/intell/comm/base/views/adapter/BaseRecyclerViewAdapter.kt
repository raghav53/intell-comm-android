package com.intell.comm.base.views.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.intell.comm.BR
import java.lang.Exception
import java.lang.NullPointerException
import java.util.ArrayList

class BaseRecyclerViewAdapter<M, B : ViewDataBinding>(
    @field:LayoutRes @param:LayoutRes private val layoutResId: Int?,
    private var modelVariableId: Int,
    private var callback: OnItemClickListener<M>? = null,
    private var isPosition: Boolean = false
) : RecyclerView.Adapter<BaseAdapterViewHolder<B>>() {

    private val dataList: MutableList<M> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapterViewHolder<B> {
        if (layoutResId == null) throw NullPointerException("Binding adapter cannot be null")
        val binding: B =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResId, parent, false)
        binding.setVariable(BR.callback, callback)
        return BaseAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseAdapterViewHolder<B>, position: Int) {
        if (isPosition) {
            holder.binding.setVariable(BR.position, position)
        }
        holder.binding.setVariable(modelVariableId, dataList[position])

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getList(): List<M> {
        return dataList
    }

    fun addList(newDataList: List<M>?) {
        if (newDataList == null) {
            return
        }
        val positionStart = dataList.size
        val itemCount = newDataList.size
        dataList.addAll(newDataList)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    fun addData(data: M) {
        dataList.add(data)
        notifyItemInserted(dataList.size - 1)
    }

    fun removeItem(i: Int) {
        try {
            if (i != -1) {
                dataList.removeAt(i)
                notifyItemRemoved(i)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newDataList: List<M>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    fun updateList(i: Int, scanResult: M?) {
        if (scanResult == null) return
        dataList.add(i, scanResult)
        notifyItemChanged(i)
    }
}