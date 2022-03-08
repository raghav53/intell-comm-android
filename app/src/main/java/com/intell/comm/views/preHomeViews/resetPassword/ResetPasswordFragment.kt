package com.intell.comm.views.preHomeViews.resetPassword

import android.text.TextUtils
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentResetPasswordBinding
import com.intell.comm.views.preHomeViews.PreHomeActivity

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding, ResetPasswordViewModel>(
    R.layout.fragment_reset_password,
    ResetPasswordViewModel::class.java
) {
    var password = ""
    var confirmPassword = ""

    override fun onCreateView() {
        (requireActivity() as PreHomeActivity).isBackArrowShow()
        (requireActivity() as PreHomeActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.reset_password)
        )
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
                R.id.btn_reset_password -> {
                    if (checkValidation()) {
                        navigateToLoginPage()
                    }
                }
            }
        }
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_password -> {
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
            binding.etPassword.error = this.getString(R.string.please_enter_your_password)
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

    private fun navigateToLoginPage() {
        val direction =
            ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
        findNavController().navigate(direction)
    }

}