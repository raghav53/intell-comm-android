package com.intell.comm.views.clientsModule

import android.view.View
import androidx.navigation.NavDirections
import com.intell.comm.R
import com.intell.comm.base.views.BaseActivity
import com.intell.comm.databinding.ActivityClientsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsActivity : BaseActivity<ActivityClientsBinding, ClientsViewModel>(
    R.layout.activity_clients,
    ClientsViewModel::class.java,
    R.id.clients_nav_host_fragment
) {
    private var fragmentId: NavDirections? = null

    fun isBackArrowShow(isBack: Boolean = false) {
        binding.isToolbarBack = isBack
    }

    fun isToolbarTitleShow(isToolbarTitle: Boolean = false, toolbarTitle: String = "") {
        binding.isToolbarTitle = isToolbarTitle
        binding.toolbarTitle = toolbarTitle
    }

    fun isToolbarTitleShow(isToolbarTitle: Boolean = false, toolbarTitle: Int) {
        binding.isToolbarTitle = isToolbarTitle
        if (toolbarTitle != -1)
            binding.toolbarTitle = this@ClientsActivity.resources.getString(toolbarTitle)
    }

    fun isAddIconShow(isAddIcon: Boolean = false, fragmentId: NavDirections? = null) {
        binding.isAddIcon = isAddIcon
        this.fragmentId = fragmentId
    }

    override fun onCreate() {
        viewModel.baseBack.observe(this) {
            onBackPressed()
        }

        viewModel.baseClick.observe(this) { view ->
            when (view?.id ?: 0) {
                R.id.iv_add -> {
                    if (fragmentId != null)
                        navigationDirection(fragmentId!!)
                }
            }
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
}