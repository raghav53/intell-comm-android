package com.intell.comm.views.clientsModule.notes

import android.view.View
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterNotesListBinding
import com.intell.comm.databinding.FragmentNotesBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>(
    R.layout.fragment_notes,
    NotesViewModel::class.java

) {

    private val args: NotesFragmentArgs by navArgs()
    var search = ""

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
        val notesListAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterNotesListBinding>(
            R.layout.adapter_notes_list,
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