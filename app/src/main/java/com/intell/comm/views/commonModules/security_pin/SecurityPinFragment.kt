package com.intell.comm.views.commonModules.security_pin

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentSecurityPinBinding
import com.intell.comm.views.commonModules.CommonModuleActivity


class SecurityPinFragment : BaseFragment<FragmentSecurityPinBinding, SecurityPinViewModel>(
    R.layout.fragment_security_pin,
    SecurityPinViewModel::class.java
) {

    var otp = ""
    val tvOtp = arrayOfNulls<TextView>(6)

    override fun onCreateView() {
        (requireActivity() as CommonModuleActivity).isBackArrowShow(true)
        (requireActivity() as CommonModuleActivity).isToolbarTitleShow(
            true,
            this.getString(R.string.security_pin)
        )
        (requireActivity() as CommonModuleActivity).isAddIconShow()
        tvOtp[0] = binding.tvOtp1
        tvOtp[1] = binding.tvOtp2
        tvOtp[2] = binding.tvOtp3
        tvOtp[3] = binding.tvOtp4
        //handle back press button
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_otp -> {
                            otp = editText.text.toString().trim()
                            for (i in 0 until 4) {
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


        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {
                R.id.btn_update -> {
                    if (checkValidation()) {
                        requireActivity().finish()
                    }

                }
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
        if (otp.length < 4) {
            Toast.makeText(requireContext(), "Please enter your OTP.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}