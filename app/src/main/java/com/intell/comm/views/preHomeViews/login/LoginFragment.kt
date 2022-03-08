package com.intell.comm.views.preHomeViews.login

import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentLoginBinding
import com.intell.comm.views.homeViews.HomeActivity
import com.intell.comm.views.preHomeViews.PreHomeActivity

class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(
        R.layout.fragment_login,
        LoginViewModel::class.java
    ) {

    var email = ""
    var password = ""

    override fun onCreateView() {
        (requireActivity() as PreHomeActivity).isBackArrowShow()
        (requireActivity() as PreHomeActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.login)
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
                R.id.btn_forgot_password -> {
                    navigateToForgotPassword()
                }
                R.id.btn_login -> {
                    if (checkValidation()) {
                        openHomePage()
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
                    }
                }

            })

        binding.check = false

    }

    private fun checkValidation(): Boolean {
        if (TextUtils.isEmpty(email)) {
            binding.etEmail.error = this.getString(R.string.please_enter_your_email)
            return false
        } else if (TextUtils.isEmpty(password)) {
            binding.etPassword.error = this.getString(R.string.please_enter_your_password)
            return false
        }
        return true
    }

    private fun navigateToForgotPassword() {
        val direction =
            LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(direction)
    }

    private fun openHomePage() {
        startNewActivity(Intent(requireContext(), HomeActivity::class.java), false)
    }

}