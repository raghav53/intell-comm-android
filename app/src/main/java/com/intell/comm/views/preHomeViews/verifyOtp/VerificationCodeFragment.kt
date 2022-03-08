package com.intell.comm.views.preHomeViews.verifyOtp

import android.os.CountDownTimer
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentVerificationCodeBinding
import com.intell.comm.views.preHomeViews.PreHomeActivity

class VerificationCodeFragment :
    BaseFragment<FragmentVerificationCodeBinding, VerificationCodeViewModel>(
        R.layout.fragment_verification_code,
        VerificationCodeViewModel::class.java
    ) {

    var otp = ""
    val tvOtp = arrayOfNulls<TextView>(6)
    private lateinit var resentTimer: CountDownTimer

    override fun onCreateView() {
        (requireActivity() as PreHomeActivity).isBackArrowShow(true)
        (requireActivity() as PreHomeActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.verify_otp)
        )
        tvOtp[0] = binding.tvOtp1
        tvOtp[1] = binding.tvOtp2
        tvOtp[2] = binding.tvOtp3
        tvOtp[3] = binding.tvOtp4
        tvOtp[4] = binding.tvOtp5
        tvOtp[5] = binding.tvOtp6

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_verify_otp -> {
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
                        R.id.et_otp -> {
                            otp = editText.text.toString().trim()
                            for (i in 0 until 6) {
                                if (i < otp.length) {
                                    tvOtp[i]!!.text = otp[i].toString()
                                } else {
                                    tvOtp[i]!!.text = ""
                                }
                                checkLength(tvOtp[i]!!)
                            }
                        }
                    }
                }

            })

        resendEnableTime()
    }

    private fun resendEnableTime() {
        binding.btnResendOtp.isEnabled = false
        resentTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.btnResendOtp.text = ("Resend OTP?   00:" + millisUntilFinished / 1000)

            }

            override fun onFinish() {
                binding.btnResendOtp.isEnabled = true
                binding.btnResendOtp.text = requireActivity().getString(R.string.resend_code)
            }
        }
    }

    private fun checkLength(textView: TextView) {
        if (textView.text.toString().trim().isEmpty()) {
            setViewBackgroundDrawable(
                textView,
                R.drawable.edit_bg_white_red
            )
        } else {
            setViewBackgroundDrawable(
                textView,
                R.drawable.edit_bg_white_green
            )
        }
    }

    private fun checkValidation(): Boolean {
        if (otp.length < 6) {
            Toast.makeText(requireContext(), "Please enter your OTP.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun navigateToResetPassword() {
        val direction =
            VerificationCodeFragmentDirections.actionVerificationCodeFragmentToResetPasswordFragment()
        findNavController().navigate(direction)
    }


}