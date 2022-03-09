package com.intell.comm.views.clientsModule.files

import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterFileListBindingImpl
import com.intell.comm.databinding.FragmentFileDetailBinding
import com.intell.comm.views.clientsModule.ClientsActivity


class FileDetailFragment : BaseFragment<FragmentFileDetailBinding, FileDetailViewModel>(
    R.layout.fragment_file_detail,
    FileDetailViewModel::class.java
) {

    private val args: FileDetailFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.fileName + ""
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setText(requireActivity().resources.getString(R.string.filed))
        );
        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setText(requireActivity().resources.getString(R.string.unfiled))
        );
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.position == 0) {
                    binding.rvFiled.visibility = View.VISIBLE
                    binding.rvUnfiled.visibility = View.GONE
                } else {
                    binding.rvFiled.visibility = View.GONE
                    binding.rvUnfiled.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        setFilesList()

    }

    private fun setFilesList() {
        val filedAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterFileListBindingImpl>(
            R.layout.adapter_files_detail_list,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    navigationDirection(FilesFragmentDirections.actionFilesToFilesDetailList(m.string1))
                }
            },
            isPosition = true
        )


        binding.rvFiled.adapter = filedAdapter
        filedAdapter.updateList(getSwipeList())

        binding.rvUnfiled.adapter = filedAdapter
        filedAdapter.updateList(getSwipeList())
    }

    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

          list.add(
            BaseModel(
                string1 = "Muralidhar Client",
                string2 = "Jackson",
                string3 = "25/10/2022 04:00 AM",
                string4 = "25/10/2022 04:00 AM"
            )
        )

        return list
    }

}