package com.intell.comm.views.homeViews.messageInbox

 
import android.view.View
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.FragmentChatBinding
 import com.intell.comm.views.homeViews.HomeActivity


class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>(
    R.layout.fragment_chat,
    ChatViewModel::class.java
) {


    override fun onCreateView() {
        (requireActivity() as HomeActivity).setVisibility()
        setClientsList()
    }

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