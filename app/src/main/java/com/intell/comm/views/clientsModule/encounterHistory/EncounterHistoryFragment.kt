package com.intell.comm.views.clientsModule.encounterHistory

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterNotesListBinding
import com.intell.comm.databinding.FragmentEncounterHistoryBinding
import com.intell.comm.views.clientsModule.ClientsActivity


class EncounterHistoryFragment :
    BaseFragment<FragmentEncounterHistoryBinding, EncounterHistoryViewModel>(
        R.layout.fragment_encounter_history,
        EncounterHistoryViewModel::class.java
    ) {

    private val args: EncounterHistoryFragmentArgs by navArgs()
    var search = ""
    private lateinit var filterSheetBehavior: BottomSheetBehavior<RelativeLayout>
    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.rl_filter -> {
                 showBottomSheetDialog("show")

                }


            }


        }

        setClientsList()
    }

    private fun showBottomSheetDialog(s: String) {
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_encounter)
        val btnSearch = bottomSheetDialog.findViewById<AppCompatButton>(R.id.btn_search)

            bottomSheetDialog.show()

        btnSearch?.setOnClickListener { bottomSheetDialog.dismiss() }
    }

    private fun setClientsList() {
        val notesListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterNotesListBinding>(
            R.layout.adapter_encounter_history_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                }
            },
            isPosition = true
        )



        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_search_notes -> {
                            search = if (editText.text.toString().trim().isEmpty()) {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_red
                                )
                                ""
                            } else {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_green
                                )
                                editText.text.toString().trim()
                            }
                        }
                    }
                }

            })

        binding.rvNotes.adapter = notesListAdapter
        notesListAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        list.add(
            BaseModel(
                string1 = "Test",
                string2 = "Test",
                string3 = "Test",
                string4 = "Test",
                string5 = "Test",
                string6 = "Acute gastroenteropathy",
                string7 = "Testing Business",
                string8 = "Tue, Jan 25,2022 12:55 PM"
            )
        )
        return list
    }


}