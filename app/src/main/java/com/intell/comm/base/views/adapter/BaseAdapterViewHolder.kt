package com.intell.comm.base.views.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapterViewHolder <B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(
    binding.root
)