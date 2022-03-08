package com.intell.comm.views.clientsModule.referral

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentEditReferralBinding
import com.intell.comm.utils.validateEmail
import com.intell.comm.utils.validateText
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.clientsModule.diseaseRegister.DiseaseRegisterListFragmentArgs


class EditReferralFragment : BaseFragment<FragmentEditReferralBinding, ReferralViewModel>(
    R.layout.fragment_edit_referral,
    ReferralViewModel::class.java
) {
    private val args: DiseaseRegisterListFragmentArgs by navArgs()


    override fun onCreateView(){
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.toolbarTitle
        )

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {

                    }
                }

            })

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_submit -> {
                         requireActivity().onBackPressed()

                }
            }
        }
    }


}