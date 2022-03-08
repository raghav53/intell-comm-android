package com.intell.comm.views.homeViews

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.intell.comm.R
import com.intell.comm.base.di.MyApplication
import com.intell.comm.base.views.BaseActivity
import com.intell.comm.databinding.ActivityHomeBinding
import com.intell.comm.utils.NoInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(
    R.layout.activity_home, HomeActivityViewModel::class.java, null
) {

    var doubleBackToExit = false

    override fun onCreate() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.selectPosition = 1

        viewModel.baseClick.observe(this) { view ->
            when (view?.id ?: 0) {
                R.id.cl_home -> {
                    openHomePage()
                }
                R.id.cl_message -> {
                    openMessagePage()
                }
                R.id.cl_notification -> {
                    openNotificationPage()
                }
                R.id.cl_account -> {
                    openAccountPage()
                }
                R.id.iv_back -> {
                    openMessagePage()
                }
            }
        }

    }

    private fun openHomePage() {
        binding.relativeLayout.visibility=View.GONE
        binding.bottomNav.selectPosition = 1
        binding.ivBack.visibility=View.GONE
        navigationDirection(R.id.navigation_home)
        binding.bottomNav.llBottomNav.visibility=View.VISIBLE
    }

    private  fun openMessagePage() {
        binding.relativeLayout.visibility=View.VISIBLE
        binding.tvTopBar.text="Message"
        binding.bottomNav.selectPosition = 2
        binding.ivBack.visibility=View.VISIBLE
        navigationDirection(R.id.navigation_message)
        binding.bottomNav.llBottomNav.visibility=View.VISIBLE
    }

    private  fun openNotificationPage() {
        binding.relativeLayout.visibility=View.VISIBLE
        binding.tvTopBar.text="Notifications"
        binding.bottomNav.selectPosition = 3
        binding.ivBack.visibility=View.GONE
        navigationDirection(R.id.navigation_notifications)
        binding.bottomNav.llBottomNav.visibility=View.VISIBLE
    }

    private fun openAccountPage() {
        binding.relativeLayout.visibility=View.VISIBLE
        binding.tvTopBar.text="Account"
        binding.bottomNav.selectPosition = 4
        binding.ivBack.visibility=View.GONE
        navigationDirection(R.id.navigation_account)
        binding.bottomNav.llBottomNav.visibility=View.VISIBLE
    }

    override fun showBottomMessage() {
        runOnUiThread {
            if (networkAvailability.isInternetAvailable()) {
                binding.flNoInternet.visibility = View.GONE
            } else {
                binding.flNoInternet.visibility = View.VISIBLE
            }
        }
    }

    override fun onBackPressed() {

        if (Navigation.findNavController(this,R.id.nav_host_fragment_activity_home)
                .currentDestination?.id == R.id.navigation_home||
            Navigation.findNavController(this,R.id.nav_host_fragment_activity_home)
                .currentDestination?.id == R.id.navigation_message ||
                Navigation.findNavController(this,R.id.nav_host_fragment_activity_home)
                .currentDestination?.id == R.id.navigation_notifications
                ||
                Navigation.findNavController(this,R.id.nav_host_fragment_activity_home)
                .currentDestination?.id == R.id.navigation_account
                ) {
            if (doubleBackToExit) {
                super.onBackPressed()
                return
            }
            doubleBackToExit = true
            viewModel.messageUtils.showToast(this@HomeActivity, "Please click back again to exit")
            Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExit = false }, 2000)

            return;
        }else{
          openMessagePage()
        }

    }

    fun setVisibility() {
        binding.bottomNav.llBottomNav.visibility=View.GONE
     }
}