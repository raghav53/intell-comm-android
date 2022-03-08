package com.intell.comm.views.commonModules

import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.intell.comm.R
import com.intell.comm.base.views.BaseActivity
 import com.intell.comm.databinding.ActivityCommonModuleBinding

class CommonModuleActivity : BaseActivity<ActivityCommonModuleBinding, CommonModuleViewModel>(
    R.layout.activity_common_module,
    CommonModuleViewModel::class.java,
    R.id.common_nav_host_fragment
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
            binding.toolbarTitle = this@CommonModuleActivity.resources.getString(toolbarTitle)
    }

    fun isAddIconShow(isAddIcon: Boolean = false, fragmentId: NavDirections? = null) {
        binding.isAddIcon = isAddIcon
        this.fragmentId = fragmentId
    }

    override fun onCreate() {
        val key:Int = intent.getIntExtra("key",0)

        setStartFragment(key)

        viewModel.baseBack.observe(this) {
            onBackPressed()
        }

        viewModel.baseClick.observe(this) { view ->
            when (view?.id ?: 0) {
                R.id.iv_edit_profile -> {
                    if (fragmentId != null)
                        navigationDirection(fragmentId!!)
                }
            }
        }

    }

//    override fun showBottomMessage() {
//        runOnUiThread {
//            if (networkAvailability.isInternetAvailable()) {
//                binding.flNoInternet.visibility = View.GONE
//            } else {
//                binding.flNoInternet.visibility = View.VISIBLE
//            }
//        }
//    }

    private fun setStartFragment(key: Int) {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.common_nav_host_fragment) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.common_nav_graph)
         if(key==0){
            graph.setStartDestination(R.id.profileFragment)

        }else if(key==1){
            graph.setStartDestination(R.id.securityPinFragment)

        }else if(key==2){
            graph.setStartDestination(R.id.changePasswordFragment)

        }
        navHostFragment.navController.graph = graph
    }

}