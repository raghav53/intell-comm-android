package com.intell.comm.views.homeViews.messageInbox

import android.view.View
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.AdapterMessageListBindingImpl
import com.intell.comm.databinding.FragmentMessageInboxBinding
import com.intell.comm.views.clientsModule.clientsList.ClientsListFragmentDirections

class MessageInboxFragment : BaseFragment<FragmentMessageInboxBinding, MessageInboxViewModel>(
    R.layout.fragment_message_inbox,
    MessageInboxViewModel::class.java
) {

    var search = ""

    override fun onCreateView() {

        setClientsList()

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_search -> {
                            search = if (editText.text.toString().trim().isEmpty()) {
                                setViewBackgroundDrawable(editText, R.drawable.edit_text_selected_red)
                                ""
                            } else {
                                setViewBackgroundDrawable(editText, R.drawable.edit_text_selected_green)
                                editText.text.toString().trim()
                            }
                        }
                    }
                }

            })

    }

    private fun setClientsList() {
        val messageListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterMessageListBindingImpl>(
            R.layout.adapter_message_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    navigateToChat(m.id)
                }
            },
            isPosition = true
        )

        binding.rvMessage.adapter = messageListAdapter
        messageListAdapter.updateList(getSwipeList())
    }

    private fun navigateToChat(clientId:Int) {
        val direction =
            MessageInboxFragmentDirections.actionNavigationMessageToNavigationChat4(clientId = clientId)
        findNavController().navigate(direction)
    }

     private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(BaseModel(string1 = "Rozy Jospeh",string2 = "11:35 AM",string3 = "Hi Rozy, the design like you",string4 = "4"))
        list.add(BaseModel(string1 = "Michell Marsh",string2 = "11:15 AM",string3 = "Hi Roz, the design like you",string4 = "5"))
        list.add(BaseModel(string1 = "Rozy Jospeh",string2 = "12:45 AM",string3 = "Hi Roz, the design like you",string4 = "2"))
        list.add(BaseModel(string1 = "Anne Marie",string2 = "01:42 AM",string3 = "Hi Rozy, the design like you",string4 = "6"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "02:45 AM",string3 = "Hi Rozy, the design like you",string4 = "4"))
        list.add(BaseModel(string1 = "Joseph Stalin",string2 = "02:43 AM",string3 = "Hi Rozy, the design like you",string4 = "3"))
        list.add(BaseModel(string1 = "Nail",string2 = "02:45 AM",string3 = "Hi Rozy, the design like you",string4 = "2"))
        list.add(BaseModel(string1 = "Rozy Jospeh",string2 = "02:41 AM",string3 = "Hi Rozy, the design like you",string4 = "1"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "03:42 AM",string3 = "Hi Rozy, the design like you",string4 = "1"))
        return list
    }

}