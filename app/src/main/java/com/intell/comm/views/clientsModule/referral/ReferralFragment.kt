package com.intell.comm.views.clientsModule.referral

import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterNotesListBinding
import com.intell.comm.databinding.FragmentReferralBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.diseaseRegister.DiseaseRegisterListFragmentDirections

class ReferralFragment : BaseFragment<FragmentReferralBinding, ReferralViewModel>(
    R.layout.fragment_referral,
    ReferralViewModel::class.java
) {
    private val args: ReferralFragmentArgs by navArgs()

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
        val referralListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterNotesListBinding>(
            R.layout.adapter_referral_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    when (v?.id) {
                        R.id.tv_edit -> {
                             navigationDirection(ReferralFragmentDirections.actionReferralToEditReferral(R.string.edit_referral_fragment))
                        }
                        R.id.tv_view -> {
                            Log.e("onItemClick","VIEW")

                        }
                        R.id.tv_delete -> {
                            Log.e("onItemClick","DELETE")

                        } R.id.tv_reply -> {
                        navigationDirection(ReferralFragmentDirections.actionReferralToReplyReferralFragment(R.string.referral_reply))

                        }

                    }
                }
            },
            isPosition = true
        )
        binding.rvReferral.adapter = referralListAdapter
        referralListAdapter.updateList(getSwipeList())


    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "Family Physicians",
                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",

                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )
        list.add(
            BaseModel(
                string1 = "Family Physicians",
                string2 = "muralidharan.client@gmail.com",
                string3 = "+918978453048",
                string4 = "01/05/200",
                string5 = "Urgent",
                string6 = "Can you se this patient please Thanks.",
                string7 = "Tue, Jan 25,2022 12:55 PM",
                string8 = "Murali Dharan"
            )
        )

        return list
    }

}