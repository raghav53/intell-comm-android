package com.intell.comm.base.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.intell.comm.BR
import java.lang.NullPointerException
import java.util.ArrayList

class BaseViewPagerAdapter<M, B : ViewDataBinding>(
    @field:LayoutRes @param:LayoutRes private val layoutResId: Int?,
    private var modelVariableId: Int,
    private var callback: OnItemClickListener<M>? = null,
    private var isPosition: Boolean = false
) : PagerAdapter(){

    private val dataList: MutableList<M> = ArrayList()

    fun updateList(newDataList: List<M>?) {
        if (newDataList == null) {
            return
        }
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }


    override fun getCount(): Int {
        return dataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (layoutResId == null) throw NullPointerException("Binding view pager cannot be null")
        val binding: B = DataBindingUtil.inflate(
            LayoutInflater.from(container.context),
            layoutResId,
            container,
            false
        )
        binding.setVariable(BR.callback, callback)
        if (isPosition) {
            binding.setVariable(BR.position, position)
        }
        binding.setVariable(modelVariableId, dataList[position])

        val pager: ViewPager = container as ViewPager
        pager.addView(binding.root, 0)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}