package com.intell.comm.views.clientsModule.files

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
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
    }

}