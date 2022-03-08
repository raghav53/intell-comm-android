package com.intell.comm.views.clientsModule.vitals

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentVitalsBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class VitalsFragment : BaseFragment<FragmentVitalsBinding, VitalsViewModel>(
    R.layout.fragment_vitals,
    VitalsViewModel::class.java
) {

    private val args: VitalsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
    }

}