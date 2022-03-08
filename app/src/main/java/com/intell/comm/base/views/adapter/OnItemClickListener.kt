package com.intell.comm.base.views.adapter

import android.view.View

interface OnItemClickListener<M> {
    fun onItemClick(v: View?, m: M, position: Int = -1)
}