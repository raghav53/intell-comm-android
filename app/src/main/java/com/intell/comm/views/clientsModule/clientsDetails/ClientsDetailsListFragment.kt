package com.intell.comm.views.clientsModule.clientsDetails

import android.view.View
import androidx.navigation.fragment.findNavController
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.FragmentClientsDetailsListBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import java.lang.NullPointerException

class ClientsDetailsListFragment :
    BaseFragment<FragmentClientsDetailsListBinding, ClientsDetailsViewModel>(
        R.layout.fragment_clients_details_list,
        ClientsDetailsViewModel::class.java
    ) {

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.client_details)
        )

        setCategoryList()

    }

    private fun setCategoryList() {
        val categoryAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterCategoriesBinding>(
            R.layout.adapter_client_details, BR.model, object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    if (m.direction == null) throw NullPointerException("Fragment NavDirections cannot be null")
                    findNavController().navigate(m.direction)
                }
            },
            isPosition = true
        )

        binding.rvClients.adapter = categoryAdapter
        categoryAdapter.updateList(getSwipeList())
    }


    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                int1 = R.drawable.ic_client,
                int2 = R.string.client_detail,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToClientDetails(
                    toolbarTitle = R.string.client_detail
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_disease_register,
                int2 = R.string.disease_register,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToDiseaseRegister(
                    R.string.disease_register
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_allery,
                int2 = R.string.allergy,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToAllergy(R.string.allergy)
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_files,
                int2 = R.string.notes,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToNotes(R.string.notes)
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_vaccine,
                int2 = R.string.vaccination,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToVaccination(
                    R.string.vaccination
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_history,
                int2 = R.string.encounter_history,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToEncounterHistory(
                    R.string.encounter_history
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_medical_appointment,
                int2 = R.string.appointments,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToAppointments(
                    R.string.appointments
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_client,
                int2 = R.string.vitals,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToVitals(R.string.vitals)
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_first_aid_box,
                int2 = R.string.medication,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToMedication(
                    R.string.medication
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_files,
                int2 = R.string.files,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToFiles(R.string.files)
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_other_party,
                int2 = R.string.other_party,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToOtherParty(
                    R.string.other_party
                )
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_referral,
                int2 = R.string.referral,
                direction = ClientsDetailsListFragmentDirections.actionClientsListDetailsToReferral(
                    R.string.referral
                )
            )
        )
        return list
    }


}