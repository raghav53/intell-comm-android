package com.intell.comm.views.clientsModule.vaccination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentAddVaccinationBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class AddVaccinationFragment : BaseFragment<FragmentAddVaccinationBinding, AddVaccinationViewModel>(
    R.layout.fragment_add_vaccination,
    AddVaccinationViewModel::class.java
) {

    val materialDateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
    val materialDatePicker = materialDateBuilder.build()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.add_vaccination)
        )
        materialDateBuilder.setTitleText("SELECT DONE DATE")

        materialDatePicker.addOnPositiveButtonClickListener {
            Toast.makeText(
                requireContext(),
                "Selected Date is : " + materialDatePicker.headerText,
                Toast.LENGTH_SHORT
            ).show()
            binding.etDoneDate.setText(materialDatePicker.headerText)
        }
        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_save -> {
                         requireActivity().onBackPressed()

                }
            }
        }
    }


}