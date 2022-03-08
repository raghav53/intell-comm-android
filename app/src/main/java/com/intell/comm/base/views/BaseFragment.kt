package com.intell.comm.base.views

import android.content.Intent
import android.graphics.Rect
import androidx.databinding.ViewDataBinding
import javax.inject.Inject
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.intell.comm.BR
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.intell.comm.BuildConfig
import com.intell.comm.R
import com.intell.comm.base.viewModel.BaseViewModel
import com.intell.comm.network.NetworkAvailability
import java.lang.NullPointerException

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel>(
    @field:LayoutRes @param:LayoutRes val layoutResId: Int?,
    private val viewModelClass: Class<V>?
) : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPref

    @Inject
    lateinit var networkAvailability: NetworkAvailability
    protected lateinit var viewModel: V
    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (layoutResId == null) throw NullPointerException("Binding fragment cannot be null")
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        if (viewModelClass != null) {
            viewModel = ViewModelProvider(this)[viewModelClass]
            binding.setVariable(BR.vm, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateView()
    }

    protected abstract fun onCreateView()

    fun navigationDirection(
        fragmentId: Int,
        args: Bundle? = null
    ) {
        findNavController().navigate(fragmentId, args)
    }

    fun navigationDirection(
        fragmentId: Int,
        args: Bundle? = null,
        enterAnim: Int = android.R.animator.fade_in,
        exitAnim: Int = android.R.animator.fade_out
    ) {
        findNavController().navigate(
            fragmentId,
            args,
            navOptions {
                anim {
                    enter = enterAnim
                    exit = exitAnim
                }
            })
    }

    fun navigationDirection(
        fragmentId: NavDirections
    ) {
        findNavController().navigate(fragmentId)
    }

    fun navigationDirection(
        fragmentId: NavDirections, enterAnim: Int = android.R.animator.fade_in,
        exitAnim: Int = android.R.animator.fade_out
    ) {
        findNavController().navigate(
            fragmentId,
            navOptions {
                anim {
                    enter = enterAnim
                    exit = exitAnim
                }
            })
    }


    fun setTextColor(editText: EditText, color: Int) {
        editText.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                color
            )
        )
    }

    fun setViewBackgroundDrawable(editText: EditText, drawable: Int) {
        editText.background =
            ContextCompat.getDrawable(requireContext(), drawable)
    }

    fun setViewBackgroundDrawable(textView: TextView, drawable: Int) {
        textView.background =
            ContextCompat.getDrawable(requireContext(), drawable)
    }

    fun startNewActivity(intent: Intent, isFinish: Boolean = false, isAnim: Boolean = true) {
        requireActivity().startActivity(intent)
        if (isFinish) {
            requireActivity().finish()
        }
        if (BuildConfig.EnableAnim && isAnim) {
            requireActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }


    open fun setListenerToRootView(editText: EditText) {
        val activityRootView: View = requireActivity().window.decorView.findViewById(R.id.content)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            activityRootView.getWindowVisibleDisplayFrame(r)
            val screenHeight = activityRootView.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom

            // 0.15 ratio is perhaps enough to determine keypad height.
            //       oftKeyboard open
            // keyboard is closed
            //       softKeyboard closed
            editText.isCursorVisible = keypadHeight > screenHeight * 0.15
        }
    }


}