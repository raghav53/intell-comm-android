package com.intell.comm.views.clientsModule.medication

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentMedicationBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class MedicationFragment : BaseFragment<FragmentMedicationBinding, MedicationViewModel>(
    R.layout.fragment_medication,
    MedicationViewModel::class.java
) {

    private val args: MedicationFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
    }

}