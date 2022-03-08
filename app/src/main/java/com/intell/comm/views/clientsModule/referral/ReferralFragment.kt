package com.intell.comm.views.clientsModule.referral

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentReferralBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class ReferralFragment : BaseFragment<FragmentReferralBinding, ReferralViewModel>(
    R.layout.fragment_referral,
    ReferralViewModel::class.java
) {

    private val args: ReferralFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
    }

}