package com.intell.comm.views.clientsModule.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentAddAllergyBinding
import com.intell.comm.utils.validateEmail
import com.intell.comm.utils.validateText
import com.intell.comm.views.clientsModule.ClientsActivity

class AddAllergyFragment : BaseFragment<FragmentAddAllergyBinding,AddAllergyViewModel>(
    R.layout.fragment_add_allergy,
    AddAllergyViewModel::class.java
) {
    private val materialDateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
    private val materialDatePicker = materialDateBuilder.build()
    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.add_allergy)
        )
        materialDateBuilder.setTitleText("SELECT START DATE")

        materialDatePicker.addOnPositiveButtonClickListener {
            Toast.makeText(
                requireContext(),
                "Selected Date is : " + materialDatePicker.headerText,
                Toast.LENGTH_SHORT
            ).show()
            binding.etStartDate.setText(materialDatePicker.headerText)
        }
        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_submit -> {
                        requireActivity().onBackPressed()

                }
                R.id.et_start_date -> {
                    materialDatePicker.show(requireActivity().supportFragmentManager, "MATERIAL_DATE_PICKER")

                }
            }
        }
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_environment_allergy -> {
                            //fName = validateText(requireActivity(), editText)
                        }
                        R.id.et_reaction -> {
                         }
                        R.id.et_component_type -> {
                         }
                        R.id.et_hiv_related -> {
                         }

                    }
                }

            })
    }


}