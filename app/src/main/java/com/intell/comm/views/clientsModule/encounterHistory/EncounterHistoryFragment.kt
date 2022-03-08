package com.intell.comm.views.clientsModule.encounterHistory

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentEncounterHistoryBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class EncounterHistoryFragment : BaseFragment<FragmentEncounterHistoryBinding, EncounterHistoryViewModel>(
    R.layout.fragment_encounter_history,
    EncounterHistoryViewModel::class.java
) {

    private val args: EncounterHistoryFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
    }

}