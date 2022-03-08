package com.intell.comm.views.clientsModule.otherParty

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentOtherPartyBinding
import com.intell.comm.utils.checkIsEmpty
import com.intell.comm.views.clientsModule.ClientsActivity

class OtherPartyFragment : BaseFragment<FragmentOtherPartyBinding, OtherPartyViewModel>(
    R.layout.fragment_other_party,
    OtherPartyViewModel::class.java
) {

    private val args: OtherPartyFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_update -> {
                    requireActivity().onBackPressed()
                }
            }
        }
    }



}