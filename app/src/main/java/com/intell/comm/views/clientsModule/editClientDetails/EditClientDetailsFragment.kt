package com.intell.comm.views.clientsModule.editClientDetails

import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentEditClientDetailsBinding
import com.intell.comm.model.SpinnerModel
import com.intell.comm.utils.checkIsEmpty
import com.intell.comm.utils.validateEmail
import com.intell.comm.utils.validateText
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.clientsAdd.MySpinnerAdapter

class EditClientDetailsFragment : BaseFragment<FragmentEditClientDetailsBinding, EditClientDetailsViewModel>(
    R.layout.fragment_edit_client_details,
    EditClientDetailsViewModel::class.java
) {

    var fName = ""
    var lName = ""
    var email = ""
    var phone = ""
    var dob = ""
    var countryName = ""
    var stateName = ""
    var city = ""
    var postalCode = ""
    var categoryName = ""
    var doctorCategoryName = ""

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.add_client)
        )

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_submit -> {
                    if (checkValidation()) {
                        requireActivity().onBackPressed()
                    }
                }
            }
        }

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_first_name -> {
                            fName = validateText(requireActivity(), editText)
                        }
                        R.id.et_last_name -> {
                            lName = validateText(requireActivity(), editText)
                        }
                        R.id.et_email -> {
                            email = validateEmail(requireActivity(), editText)
                        }
                        R.id.et_phone -> {
                            phone = validateText(requireActivity(), editText)
                        }
                        R.id.et_dob -> {
                            dob = validateText(requireActivity(), editText)
                        }
                    }
                }

            })

        spinnerCountry()
        spinnerState()

    }

    private fun checkValidation(): Boolean {
        if (checkIsEmpty(viewModel, fName, binding.parent)) {
            binding.etFirstName.error = this.getString(R.string.enter_first_name)
            return false
        } else if (checkIsEmpty(viewModel, lName, binding.parent)) {
            binding.etLastName.error = this.getString(R.string.enter_last_name)
            return false
        }
        return true
    }


    private fun spinnerCountry() {
        val countryList = resources.getStringArray(R.array.CountryList)

        val spinnerCountryList = ArrayList<SpinnerModel>()

        for (i in countryList.indices) {
            spinnerCountryList.add(SpinnerModel(countryList[i], false))
        }
        val adapter =
            MySpinnerAdapter(requireContext(), R.layout.spinner_layout, spinnerCountryList)

        adapter.setDropDownViewResource(R.layout.spinner_drop_down_layout)

        binding.spCountry.adapter = adapter

        var previousSelection = 0

        binding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                adapter.spinnerList[previousSelection].isSelected = false
                adapter.spinnerList[p0?.selectedItemPosition!!].isSelected = true
                previousSelection = p0.selectedItemPosition
                countryName = adapter.spinnerList[p0.selectedItemPosition].title
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        for (i in countryList.indices) {
            if (countryList[i].equals(countryName)) {
                binding.spCountry.setSelection(i)
                break
            }
        }

    }

    private fun spinnerState() {
        val stateList = resources.getStringArray(R.array.StateList)

        val spinnerStateList = ArrayList<SpinnerModel>()

        for (i in stateList.indices) {
            spinnerStateList.add(SpinnerModel(stateList[i], false))
        }
        val adapter =
            MySpinnerAdapter(requireContext(), R.layout.spinner_layout, spinnerStateList)

        adapter.setDropDownViewResource(R.layout.spinner_drop_down_layout)

        binding.spState.adapter = adapter

        var previousSelection = 0

        binding.spState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                adapter.spinnerList[previousSelection].isSelected = false
                adapter.spinnerList[p0?.selectedItemPosition!!].isSelected = true
                previousSelection = p0.selectedItemPosition
                stateName = adapter.spinnerList[p0.selectedItemPosition].title
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        for (i in stateList.indices) {
            if (stateList[i].equals(stateName)) {
                binding.spState.setSelection(i)
                break
            }
        }

    }

}