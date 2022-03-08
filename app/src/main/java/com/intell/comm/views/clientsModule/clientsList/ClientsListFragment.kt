package com.intell.comm.views.clientsModule.clientsList

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
import com.intell.comm.databinding.FragmentClientsListBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class ClientsListFragment : BaseFragment<FragmentClientsListBinding, ClientsListViewModel>(
    R.layout.fragment_clients_list,
    ClientsListViewModel::class.java
) {

    var search = ""

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow(true,
            ClientsListFragmentDirections.actionClientsListFragmentToClientAddFragment())
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.clients)
        )

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

        setClientsList()
    }

    private fun setClientsList() {
        val clientListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterCategoriesBinding>(
            R.layout.adapter_clients_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    navigateToClientsListDetails(m.id)
                }
            },
            isPosition = true
        )

        binding.rvClients.adapter = clientListAdapter
        clientListAdapter.updateList(getSwipeList())
    }

    private fun navigateToClientsListDetails(clientId:Int) {
        val direction =
            ClientsListFragmentDirections.actionClientsListFragmentToClientDetailsListFragment(clientId = clientId)
        findNavController().navigate(direction)
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        list.add(BaseModel(string1 = "Jackson Bay",string2 = "10/12/21",string3 = "jackson@yopmail.com",string4 = "ALCL0202060909"))
        return list
    }

}