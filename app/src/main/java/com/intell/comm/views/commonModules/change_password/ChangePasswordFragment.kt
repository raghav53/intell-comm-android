package com.intell.comm.views.commonModules.change_password

import android.text.TextUtils
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
 import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentChangePasswordBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import com.intell.comm.views.commonModules.CommonModuleActivity

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, ChangePasswordFragmentViewModel>(
    R.layout.fragment_change_password,
    ChangePasswordFragmentViewModel::class.java
) {

    var password = ""
    var confirmPassword = ""

    override fun onCreateView() {
        (requireActivity() as CommonModuleActivity).isBackArrowShow(true)
        (requireActivity() as CommonModuleActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.change_password)
        )
        (requireActivity() as CommonModuleActivity).isAddIconShow()

        //handle back press button
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_change_password -> {
                    if (checkValidation()) {
                        //navigateToLoginPage()
                        requireActivity().finish()
                    }
                }
            }
        }
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_current_password -> {
                            password = if (editText.text.toString().trim().length < 6) {
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
                        R.id.et_confirm_password -> {
                            confirmPassword = editText.text.toString().trim()
                            if (password != confirmPassword) {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_red
                                )
                            } else {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_green
                                )
                            }
                        }
                    }
                }

            })

    }

    private fun checkValidation(): Boolean {
        if (TextUtils.isEmpty(password)) {
            binding.etCurrentPassword.error = this.getString(R.string.please_enter_your_password)
            return false
        } else if (TextUtils.isEmpty(confirmPassword)) {
            binding.etConfirmPassword.error =
                this.getString(R.string.please_enter_your_confirm_password)
            return false
        } else if (password != confirmPassword) {
            binding.etConfirmPassword.error = this.getString(R.string.password_not_match)
            return false
        }
        return true
    }

//    private fun navigateToLoginPage() {
//        val direction =
//            ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
//        findNavController().navigate(direction)
//    }

}