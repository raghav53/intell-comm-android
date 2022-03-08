package com.intell.comm.views.clientsModule.allergy

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
import com.intell.comm.databinding.FragmentAllergyBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class AllergyFragment : BaseFragment<FragmentAllergyBinding, AllergyViewModel>(
    R.layout.fragment_allergy,
    AllergyViewModel::class.java
) {

    private val args: AllergyFragmentArgs by navArgs()
    var search = ""

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow(
            true,
            AllergyFragmentDirections.actionAllergyToAddAllergy(R.string.add_allergy)
        )
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
            R.layout.adapter_allergy_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                }
            },
            isPosition = true
        )

        binding.rvAllergy.adapter = clientListAdapter
        clientListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        list.add(
            BaseModel(
                string1 = "Amoxil Chewable",
                string2 = "13/12/21",
                string3 = "08/01/2021",
                string4 = "Least Severity",
                string5 = "Jackson Bay"
            )
        )
        return list
    }


}