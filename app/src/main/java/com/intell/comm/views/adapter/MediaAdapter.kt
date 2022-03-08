package com.intell.comm.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.intell.comm.R
import com.intell.comm.databinding.AdapterMediaBinding
import com.intell.comm.model.MediaModel

class MediaAdapter(var callback: Callback) :
    RecyclerView.Adapter<MediaAdapter.MyHolder>() {
    private var mediaList = ArrayList<MediaModel>()

    fun getList(): ArrayList<MediaModel> {
        return mediaList
    }

    fun updateList(list: ArrayList<MediaModel>) {
        this.mediaList = list
        notifyDataSetChanged()
    }

    fun addList(list: ArrayList<MediaModel>) {
        this.mediaList.addAll(list)
        notifyDataSetChanged()
    }

    fun setPosition(mediaModel: MediaModel) {
        this.mediaList[mediaList.size - 1] = mediaModel
        this.mediaList.add(MediaModel())
        notifyDataSetChanged()
    }

    fun addModel(model: MediaModel) {
        this.mediaList.add(model)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position >= 0) {
            this.mediaList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    interface Callback {
        fun onClick(model: MediaModel, position: Int)
    }

    class MyHolder(_binding: AdapterMediaBinding) : RecyclerView.ViewHolder(_binding.root) {
        val binding = _binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding: AdapterMediaBinding =
            AdapterMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.ivMedia.setOnClickListener {
            if (mediaList[position].type == 0) {
                callback.onClick(mediaList[position], position)
            }
        }

        holder.binding.ivClear.setOnClickListener {
            callback.onClick(mediaList[position], position)
        }

        holder.binding.ivClear.visibility = View.VISIBLE

        if (mediaList[position].type == 0) {
            holder.binding.ivClear.visibility = View.GONE
            holder.binding.ivMedia.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.binding.ivMedia.context,
                    R.drawable.ic_baseline_add_24
                )
            )
        } else if (mediaList[position].type == 1) {//local image
            holder.binding.ivMedia.setImageBitmap(mediaList[position].bitmap)
        } else if (mediaList[position].type == 2) {//live url link
            Log.e("MediaAdapter", "onBindViewHolder: " + mediaList[position].path)
        }
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }
}