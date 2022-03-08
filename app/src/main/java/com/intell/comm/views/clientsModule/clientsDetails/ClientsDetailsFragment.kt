package com.intell.comm.views.clientsModule.clientsDetails

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentClientsDetailsBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class ClientsDetailsFragment : BaseFragment<FragmentClientsDetailsBinding, ClientsDetailsViewModel>(
    R.layout.fragment_clients_details,
    ClientsDetailsViewModel::class.java
) {

    private val args: ClientsDetailsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_edit -> {
                    navigationDirection(
                        ClientsDetailsFragmentDirections.actionClientDetailsToClientDetailsEdit(
                            R.string.edit_client_details
                        )
                    )
                }
            }
        }
    }

}