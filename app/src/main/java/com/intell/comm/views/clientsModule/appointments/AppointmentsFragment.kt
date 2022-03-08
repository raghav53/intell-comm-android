package com.intell.comm.views.clientsModule.appointments

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentAppointmentsBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class AppointmentsFragment : BaseFragment<FragmentAppointmentsBinding, AppointmentsViewModel>(
    R.layout.fragment_appointments,
    AppointmentsViewModel::class.java
) {

    private val args: AppointmentsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
    }

}