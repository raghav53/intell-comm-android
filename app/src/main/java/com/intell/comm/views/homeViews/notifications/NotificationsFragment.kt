package com.intell.comm.views.homeViews.notifications

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.datepicker.MaterialDatePicker
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.FragmentNotificationsBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>(
        R.layout.fragment_notifications,
        NotificationsViewModel::class.java
    ) {

    var search = ""

    override fun onCreateView() {



        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_search -> {
                            search = editText.text.toString().trim()
                        }
                    }
                }

            })

        setNotificationsList()
    }

    private fun setNotificationsList() {
        val notificationListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterCategoriesBinding>(
            R.layout.adapter_notification_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    Toast.makeText(requireContext(), "Notification Click", Toast.LENGTH_SHORT).show()

                }
            },
            isPosition = true
        )

        binding.rvNotifications.adapter = notificationListAdapter
        binding.rvNotifications.addItemDecoration(
            DividerItemDecoration(
                binding.rvNotifications.context,
                DividerItemDecoration.VERTICAL
            )
        )
        notificationListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(BaseModel(string1 = "Ammy Langly",string2 = "Sent you a invitation",string3 = "1 min ago"))
        list.add(BaseModel(string1 = "Rambo Nicolas",string2 = "Sent you a invitation",string3 = "2 min ago"))
        list.add(BaseModel(string1 = "Yle Poria",string2 = "Sent you a invitation",string3 = "3 min ago"))
        list.add(BaseModel(string1 = "Dike Omat",string2 = "Sent you a invitation",string3 = "4 min ago"))
        list.add(BaseModel(string1 = "Ammy Langly",string2 = "Sent you a invitation",string3 = "5 min ago"))
        list.add(BaseModel(string1 = "Yle Langly",string2 = "Sent you a invitation",string3 = "6 min ago"))
        list.add(BaseModel(string1 = "Ammy Langly",string2 = "Sent you a invitation",string3 = "5 min ago"))
        list.add(BaseModel(string1 = "Dike Langly",string2 = "Sent you a invitation",string3 = "8 min ago"))
        list.add(BaseModel(string1 = "Yle Langly",string2 = "Sent you a invitation",string3 = "9 min ago"))
        list.add(BaseModel(string1 = "Ammy Langly",string2 = "Sent you a invitation",string3 = "1 min ago"))
        list.add(BaseModel(string1 = "Yle Langly",string2 = "Sent you a invitation",string3 = "3 min ago"))
        list.add(BaseModel(string1 = "Dike Langly",string2 = "Sent you a invitation",string3 = "4 min ago"))
        list.add(BaseModel(string1 = "Yle Langly",string2 = "Sent you a invitation",string3 = "5 min ago"))

        return list
    }
}