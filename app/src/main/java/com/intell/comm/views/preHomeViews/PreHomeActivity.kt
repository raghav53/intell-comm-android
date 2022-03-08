package com.intell.comm.views.preHomeViews

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.intell.comm.R
import com.intell.comm.base.views.BaseActivity
import com.intell.comm.databinding.ActivityPreHomeBinding
import com.intell.comm.network.IS_FIRST_TIME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreHomeActivity : BaseActivity<ActivityPreHomeBinding, PreHomeViewModel>(
    R.layout.activity_pre_home,
    PreHomeViewModel::class.java, null
) {

    override fun onStart() {
        super.onStart()
        val permissionCheck = ContextCompat.checkSelfPermission(
            this@PreHomeActivity,
            Manifest.permission.WRITE_APN_SETTINGS
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //requesting permission
            ActivityCompat.requestPermissions(
                this@PreHomeActivity,
                arrayOf(Manifest.permission.WRITE_APN_SETTINGS),
                1
            )
        }
    }

    fun isBackArrowShow(isBack: Boolean = false) {
        binding.isToolbarBack = isBack
    }

    fun isToolbarTitleShow(isToolbarTitle: Boolean = false, toolbarTitle: String = "") {
        binding.isToolbarTitle = isToolbarTitle
        binding.toolbarTitle = toolbarTitle
    }

    override fun onCreate() {
        viewModel.baseBack.observe(this) {
            onBackPressed()
        }

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

    private fun setStartFragment() {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.pre_home_nav_graph)
        graph.setStartDestination(R.id.loginFragment)
        navHostFragment.navController.graph = graph
    }
}