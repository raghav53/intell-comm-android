package com.intell.comm.views.preHomeViews.welcome

import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.intell.comm.R
import com.intell.comm.base.views.BaseActivity
import com.intell.comm.databinding.ActivityWelcomeBinding
import com.intell.comm.views.preHomeViews.PreHomeActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>(
    R.layout.activity_welcome,
    WelcomeViewModel::class.java, null
) {

    override fun onCreate() {

        viewModel.baseBack.observe(this) {
            onBackPressed()
        }
        binding.viewPager.adapter = WelcomeViewPagerAdapter()
        binding.dotsIndicator.setViewPager(binding.viewPager)

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    binding.btnLogin.text = this@WelcomeActivity.getString(R.string.login)
                } else {
                    binding.btnLogin.text = this@WelcomeActivity.getString(R.string.next)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewModel.baseClick.observe(this@WelcomeActivity) { view ->
            when (view?.id ?: 0) {
                R.id.btn_login -> {
                    if (binding.viewPager.currentItem == 1) {
                        openLoginPage()
                    } else {
                        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                    }
                }
                R.id.btn_skip -> {
                    openLoginPage()
                }
            }
        }

    }


    private fun openLoginPage() {
        startNewActivity(Intent(this@WelcomeActivity, PreHomeActivity::class.java), false)
    }


}