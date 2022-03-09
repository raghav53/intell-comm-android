package com.intell.comm.views.clientsModule.vitals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentClientsAddBinding
import com.intell.comm.databinding.FragmentEditVitalsBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.clientsAdd.ClientsAddViewModel


class EditVitalsFragment : BaseFragment<FragmentEditVitalsBinding, EditVitalViewModel>(
    R.layout.fragment_edit_vitals,
    EditVitalViewModel::class.java
) {
    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.edit_vitals)
        )
        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_save -> {
                    requireActivity().onBackPressed()
                }
            }
        }
    }


}