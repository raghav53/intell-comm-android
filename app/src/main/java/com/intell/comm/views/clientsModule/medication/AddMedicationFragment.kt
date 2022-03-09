package com.intell.comm.views.clientsModule.medication

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentAddMedicationBinding
import com.intell.comm.utils.validateEmail
import com.intell.comm.utils.validateText
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.clientsDetails.ClientsDetailsFragmentArgs


class AddMedicationFragment : BaseFragment<FragmentAddMedicationBinding, AddMedicationViewModel>(
    R.layout.fragment_add_medication, AddMedicationViewModel::class.java
) {

    private val args: AddMedicationFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(args.toolbarTitle)
        )
        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_save -> {
                    requireActivity().onBackPressed()
                }
            }
        }

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {

                    }
                }

            })
    }

 }