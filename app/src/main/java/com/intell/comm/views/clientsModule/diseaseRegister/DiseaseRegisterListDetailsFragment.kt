package com.intell.comm.views.clientsModule.diseaseRegister

import android.view.View
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterDiseaseRegisterDetailsBinding
import com.intell.comm.databinding.FragmentDiseaseRegisterListDetailsBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class DiseaseRegisterListDetailsFragment :
    BaseFragment<FragmentDiseaseRegisterListDetailsBinding, DiseaseRegisterViewModel>(
        R.layout.fragment_disease_register_list_details,
        DiseaseRegisterViewModel::class.java
    ) {

    private val args: DiseaseRegisterListDetailsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.diseaseName + " Disease Register"
        )
        setDiseaseRegisterDetailsList()
    }


    private fun setDiseaseRegisterDetailsList() {
        val diseaseListAdapter =
            BaseRecyclerViewAdapter<BaseModel, AdapterDiseaseRegisterDetailsBinding>(
                R.layout.adapter_disease_register_details,
                BR.model,
                object : OnItemClickListener<BaseModel> {
                    override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                        navigationDirection(
                            DiseaseRegisterListDetailsFragmentDirections.actionDiseaseRegisterListDetailsToDiseaseRegisterDetails(
                                args.diseaseName
                            )
                        )
                    }
                },
                isPosition = true
            )

        binding.rvDiseaseDetails.adapter = diseaseListAdapter
        diseaseListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Cutaneous and mucocutaneous bartonellosis",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Syphilis, unspecified",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Cutaneous and mucocutaneous bartonellosis",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Syphilis, unspecified",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Cutaneous and mucocutaneous bartonellosis",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Syphilis, unspecified",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Cutaneous and mucocutaneous bartonellosis",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Syphilis, unspecified",
                string2 = "Tue, Jan 25, 2022 12:25 PM"
            )
        )

        return list
    }

}