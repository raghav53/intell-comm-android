package com.intell.comm.views.preHomeViews.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.intell.comm.R
import com.intell.comm.databinding.PagerWelcomeBinding

class WelcomeViewPagerAdapter : PagerAdapter() {

    private val imgArr = intArrayOf(R.drawable.ic_doctors_pana, R.drawable.ic_online_doctor_rafiki)

    private val titleArr =
        arrayOf("Welcome to IntellCOMM", "Innovative Reliable Solution For School Profession")
    private val descArr = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation."
    )

    override fun getCount(): Int {
        return titleArr.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: PagerWelcomeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container.context),
            R.layout.pager_welcome,
            container,
            false
        )

        binding.img = imgArr[position]
        binding.title = titleArr[position]
        binding.desc = descArr[position]

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