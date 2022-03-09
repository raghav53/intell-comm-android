package com.intell.comm.views.clientsModule.medication

import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterNotesListBinding
import com.intell.comm.databinding.FragmentMedicationBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.referral.ReferralFragmentDirections
import com.intell.comm.views.clientsModule.vaccination.VaccinationFragmentDirections

class MedicationFragment : BaseFragment<FragmentMedicationBinding, MedicationViewModel>(
    R.layout.fragment_medication,
    MedicationViewModel::class.java
) {

    private val args: MedicationFragmentArgs by navArgs()
    var search = ""

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow(
            true,
            MedicationFragmentDirections.actionMedicationToAddMedication(R.string.add_medication)
        )
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

       binding.tabLayout.addTab(binding.tabLayout.newTab().setText(requireActivity().resources.getString(R.string.medication)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(requireActivity().resources.getString(R.string.medication_request)))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.position == 0) {
                    binding.rvMedication.visibility = View.VISIBLE
                    binding.rvMedicationRequest.visibility = View.GONE
                } else {
                    binding.rvMedication.visibility = View.GONE
                    binding.rvMedicationRequest.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        setClientsList()

    }
    private fun showDialog(title: String) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.common_dialog)
        val body = dialog.findViewById(R.id.tv_body) as TextView
         body.text = title
        val yesBtn = dialog.findViewById(R.id.tv_delete) as TextView
        val noBtn = dialog.findViewById(R.id.tv_cancel) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    private fun setClientsList() {
        val medicationListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterNotesListBinding>(
            R.layout.adapter_medication_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    when (v?.id) {
                        R.id.tv_edit_medication -> {
                            navigationDirection(MedicationFragmentDirections.actionMedicationToAddMedication(R.string.edit_medication))
                        }
                        R.id.tv_delete_medication -> {
                            showDialog("You want to delete this medication ?")
                        }
                        }
                }
            },
            isPosition = true
        )
        val medicationRequestListAdapter =
            BaseRecyclerViewAdapter<BaseModel, AdapterNotesListBinding>(
                R.layout.adapter_medication_request_list,
                BR.model,
                object : OnItemClickListener<BaseModel> {
                    override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    }
                },
                isPosition = true
            )



        binding.rvMedication.adapter = medicationListAdapter
        medicationListAdapter.updateList(getSwipeList())

        medicationRequestListAdapter.updateList(getMedicationRequest())
        binding.rvMedicationRequest.adapter = medicationRequestListAdapter
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",
                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",
                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",
                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = requireActivity().resources.getString(R.string.pat_galantamine_er),

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        return list
    }

    private fun getMedicationRequest(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",
                string2 = "Jackson",
                string3 = "Doctor",
                string4 = "Tue, Jan 25,2022 12:55 PM",
                string5 = "Repeat Medications",
            )
        )
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",

                string2 = "Tablets",
                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",

                string2 = "Tablets",
                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "08 Mositure Foundation Spf 15",

                string2 = "Tablets",

                string3 = "10 Days",
                string4 = "Active",
                string5 = "Jackson Instruction",
                string6 = "Jackson Comment",
                string7 = "Jackson",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        return list
    }

}