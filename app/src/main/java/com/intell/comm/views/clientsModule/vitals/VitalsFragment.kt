package com.intell.comm.views.clientsModule.vitals

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterAllergyListBinding
import com.intell.comm.databinding.FragmentVitalsBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.clientsList.ClientsListFragmentDirections
import com.intell.comm.views.clientsModule.referral.ReferralFragmentDirections
import java.lang.NullPointerException

class VitalsFragment : BaseFragment<FragmentVitalsBinding, VitalsViewModel>(
    R.layout.fragment_vitals,
    VitalsViewModel::class.java
) {

    private val args: VitalsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

        setClientsList()
    }

    private fun setClientsList() {
        val clientListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterAllergyListBinding>(
            R.layout.adapter_vitals_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    when (v?.id) {
                        R.id.iv_edit_vital -> {
                            navigateToEditVitals(m.id)
                        }
                    }

                }
            },
            isPosition = true
        )

        binding.rvVitals.adapter = clientListAdapter
        clientListAdapter.updateList(getSwipeList())
    }
    private fun navigateToEditVitals(clientId:Int) {
        val direction =
            VitalsFragmentDirections.actionVitalsToEditVitals(clientId = clientId)
        findNavController().navigate(direction)
    }
    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "190/80",
                string2 = "Regular",
                string3 = "170cm",
                string4 = "70kg",
                string5 = "Sun, Jan 16, 2022 10:30 PM"
            )
        )

        return list
    }


}