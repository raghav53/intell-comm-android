package com.intell.comm.views.preHomeViews.forgotPassword

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentForgotPasswordBinding
import com.intell.comm.views.preHomeViews.PreHomeActivity

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(
        R.layout.fragment_forgot_password,
        ForgotPasswordViewModel::class.java
    ) {

    var email = ""

    override fun onCreateView() {
        (requireActivity() as PreHomeActivity).isBackArrowShow(true)
        (requireActivity() as PreHomeActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.forgot_password)
        )

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_get_otp -> {
                    if (checkValidation()) {
                        navigateToResetPassword()
                    }
                }
            }
        }
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_email -> {
                            email = if (editText.text.toString().trim().length < 6) {
                                setViewBackgroundDrawable(
                                    editText,
                                    R.drawable.edit_text_selected_red
                                )
                                ""
                            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                                    editText.text.toString().trim()
                                )
                                    .matches()
                            ) {
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
    }

    private fun checkValidation(): Boolean {
        if (TextUtils.isEmpty(email)) {
            binding.etEmail.error = this.getString(R.string.please_enter_your_email)
            return false
        }
        return true
    }


    private fun navigateToResetPassword() {
        val direction =
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToVerificationCodeFragment(
                email = email,
                otp = "1234"
            )
        findNavController().navigate(direction)
    }

}