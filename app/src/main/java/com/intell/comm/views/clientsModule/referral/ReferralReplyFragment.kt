package com.intell.comm.views.clientsModule.referral

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.FragmentReferralReplyBinding

class ReferralReplyFragment : BaseFragment<FragmentReferralReplyBinding, ReferralViewModel>(
    R.layout.fragment_referral_reply,
    ReferralViewModel::class.java
) {

    override fun onCreateView() {
        setClientsList() }
    private fun setClientsList() {
        val clientListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterCategoriesBinding>(
            R.layout.adapter_chat,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
//                    navigateToClientsListDetails(m.id)
                }
            },
            isPosition = true
        )

        binding.rvChat.adapter = clientListAdapter
        clientListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "It is a long established fact that a radar will distracted by readable content",
                string2 = ""
            )
        )
        list.add(
            BaseModel(
                string1 = "Ok test sample message",
                string2 = ""
            )
        )

        return list
    }

}