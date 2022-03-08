package com.intell.comm.views.clientsModule.diseaseRegister

import android.view.View
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterDiseaseRegisterBinding
import com.intell.comm.databinding.FragmentDiseaseRegisterListBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class DiseaseRegisterListFragment :
    BaseFragment<FragmentDiseaseRegisterListBinding, DiseaseRegisterViewModel>(
        R.layout.fragment_disease_register_list,
        DiseaseRegisterViewModel::class.java
    ) {

    private val args: DiseaseRegisterListFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
        setDiseaseList()
    }


    private fun setDiseaseList() {
        val diseaseListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterDiseaseRegisterBinding>(
            R.layout.adapter_disease_register,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    navigationDirection(DiseaseRegisterListFragmentDirections.actionDiseaseRegisterListToDiseaseRegisterListDetails(m.string1))
                }
            },
            isPosition = true
        )

        binding.rvDisease.adapter = diseaseListAdapter
        diseaseListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Minor",
                string2 = "20"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Major",
                string2 = "20"
            )
        )

        return list
    }

}