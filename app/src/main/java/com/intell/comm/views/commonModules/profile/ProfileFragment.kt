package com.intell.comm.views.commonModules.profile

import androidx.navigation.fragment.NavHostFragment
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentProfileBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.clientsList.ClientsListFragmentDirections
import com.intell.comm.views.commonModules.CommonModuleActivity


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    R.layout.fragment_profile,
    ProfileViewModel::class.java
) {


    override fun onCreateView() {
        (requireActivity() as CommonModuleActivity).isAddIconShow(
            true,
            ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment2()
        )


        (requireActivity() as CommonModuleActivity).isBackArrowShow(true)
        (requireActivity() as CommonModuleActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.profile)

        )
    }


}