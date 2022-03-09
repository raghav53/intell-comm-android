package com.intell.comm.views.clientsModule.files

import android.view.View
import androidx.navigation.fragment.navArgs
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
 import com.intell.comm.databinding.AdapterFileListBindingImpl
import com.intell.comm.databinding.FragmentFilesBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class FilesFragment : BaseFragment<FragmentFilesBinding, FilesViewModel>(
    R.layout.fragment_files,
    FilesViewModel::class.java
) {

    private val args: FilesFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )
        setFilesList()
    }


    private fun setFilesList() {
        val filesAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterFileListBindingImpl>(
            R.layout.adapter_file_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    navigationDirection(FilesFragmentDirections.actionFilesToFilesDetailList(m.string1))
                }
            },
            isPosition = true
        )

        binding.rvFile.adapter = filesAdapter
        filesAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                int1 = R.drawable.ic_account_white,
                string1 = "Lab Reports",
                string2 = "10"
            )
        )
        list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Hospital Letter",
                string2 = "3"
            )
        )

       list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Other Investigations",
                string2 = "5"
            )
        )

       list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Prescriptions",
                string2 = "8"
            )
        )

       list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Other Documents",
                string2 = "15"
            )
        )

       list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Clients",
                string2 = "4"
            )
        )

       list.add(
            BaseModel(
                int1 = R.drawable.ic_bell_white,
                string1 = "Referral",
                string2 = "1"
            )
        )



        return list
    }

}