package com.intell.comm.views.clientsModule.vaccination

import android.view.View
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterAllergyListBinding
import com.intell.comm.databinding.FragmentVaccinationBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class VaccinationFragment : BaseFragment<FragmentVaccinationBinding, VaccinationViewModel>(
    R.layout.fragment_vaccination,
    VaccinationViewModel::class.java
) {

    private val args: VaccinationFragmentArgs by navArgs()
    var search = ""

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_search -> {
                            search = if (editText.text.toString().trim().isEmpty()) {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_red
                                )
                                ""
                            } else {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_green
                                )
                                editText.text.toString().trim()
                            }
                        }
                    }
                }

            })

        setClientsList()
    }
    private fun setClientsList() {
        val clientListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterAllergyListBinding>(
            R.layout.adapter_vaccination_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                }
            },
            isPosition = true
        )

        binding.rvVaccination.adapter = clientListAdapter
        clientListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "Covid-Shield",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
             )
        )
        list.add(
            BaseModel(
                string1 = "COVAX",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
             )
        )
        list.add(
            BaseModel(
                string1 = "Coronill",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "COVOVAX",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Corbevax",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Moderna",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Sputnik V",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Ad26.COV2.S",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Vaxzevria",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Covishield",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Covaxin",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Corbevax",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Nuvaxovid",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "AKS-452",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/2022",
                string3 = "2927 Taylor Street, New York",
                string4 = "13/12/22",
                string5 = "23/12/22",
                string6 = "Jackson Bay"
            )
        )
        return list
    }


}