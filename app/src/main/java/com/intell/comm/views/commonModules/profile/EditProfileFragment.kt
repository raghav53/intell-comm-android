package com.intell.comm.views.commonModules.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentEditProfileBinding
import com.intell.comm.utils.checkIsEmpty
import com.intell.comm.utils.validateEmail
import com.intell.comm.utils.validateText
import com.intell.comm.views.commonModules.CommonModuleActivity
import java.util.*


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding,EditProfileViewModel>(
    R.layout.fragment_edit_profile,
    EditProfileViewModel::class.java
)

{
    var fName = ""
    val materialDateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
    val materialDatePicker = materialDateBuilder.build()



    override fun onCreateView() {
        (requireActivity() as CommonModuleActivity).isAddIconShow()
        (requireActivity() as CommonModuleActivity).isBackArrowShow(true)
        (requireActivity() as CommonModuleActivity).isToolbarTitleShow(true, this.getString(R.string.edit_profile))
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_name -> {
                            fName = validateText(requireActivity(), editText)
                        }
//                        R.id.et_last_name -> {
//                            lName = validateText(requireActivity(), editText)
//                        }
//                        R.id.et_email -> {
//                            email = validateEmail(requireActivity(), editText)
//                        }
//                        R.id.et_phone -> {
//                            phone = validateText(requireActivity(), editText)
//                        }
//                        R.id.et_dob -> {
//                            dob = validateText(requireActivity(), editText)
//                        }
                    }
                }

            })


        val calendar = Calendar.getInstance()
        val upTo = calendar.timeInMillis
        calendar.set(2020, 1, 15)
        val startFrom = calendar.timeInMillis
        val constraints = CalendarConstraints.Builder()
            .setStart(startFrom)
            .setEnd(upTo)
            .build()

        materialDateBuilder.setTitleText("SELECT DATE OF BIRTH")
materialDateBuilder.setCalendarConstraints(constraints)
        materialDatePicker.addOnPositiveButtonClickListener {
            Toast.makeText(
                requireContext(),
                "Selected Date is : " + materialDatePicker.headerText,
                Toast.LENGTH_SHORT
            ).show()

             binding.etDob.setText(materialDatePicker.headerText)
        }

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_submit -> {
                    if (checkValidation()) {
                        requireActivity().onBackPressed()
                    }
                }
                R.id.et_dob -> {
                    materialDatePicker.show(requireActivity().supportFragmentManager, "MATERIAL_DATE_PICKER")

                }
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (checkIsEmpty(viewModel, fName, binding.parent)) {
            binding.etName.error = this.getString(R.string.enter_first_name)
            return false
        }
        return true
    }
}