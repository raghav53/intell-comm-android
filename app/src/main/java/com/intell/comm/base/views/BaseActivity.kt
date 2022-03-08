package com.intell.comm.base.views

import android.content.Intent
import android.graphics.Rect
import androidx.databinding.ViewDataBinding
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import androidx.annotation.CallSuper
import android.os.Bundle
import android.util.Log
import com.intell.comm.BR
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.intell.comm.BuildConfig
import com.intell.comm.R
import com.intell.comm.base.di.MyApplication
import com.intell.comm.base.viewModel.BaseViewModel
import com.intell.comm.network.NetworkAvailability
import com.intell.comm.utils.NoInternetConnection
import java.lang.NullPointerException

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel>(
    @field:LayoutRes @param:LayoutRes val layoutResId: Int?,
    private val viewModelClass: Class<V>?,
    @field:IdRes @param:IdRes private val navHostIdRes: Int?
) : AppCompatActivity() {

    @Inject
    lateinit var sharedPref: SharedPref

    @Inject
    lateinit var networkAvailability: NetworkAvailability

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    lateinit var navController: NavController


    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutResId == null) throw NullPointerException("Binding activity cannot be null")
        binding = DataBindingUtil.setContentView(this, layoutResId)
        if (viewModelClass != null) {
            viewModel = ViewModelProvider(this)[viewModelClass]
            binding.setVariable(BR.vm, viewModel)
        }

        if (navHostIdRes != null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(navHostIdRes) as NavHostFragment
            navController = navHostFragment.navController
        }
        onCreate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //check internet availability
        showBottomMessage()
        (application as MyApplication).myApplication?.noInternetConnection =
            object : NoInternetConnection {
                override fun internetAvailableStatus(isInternet: Boolean) {
                    showBottomMessage()
                }
            }
    }

    protected abstract fun onCreate()

    protected open fun showBottomMessage(){
    }


    fun navigationDirection(
        fragmentId: Int,
        args: Bundle? = null, removeBackStack: Boolean = false
    ) {
        for (i in navController.backQueue.indices){
            Log.e("HomeActivity", "navigationDirection: " + navController.backQueue[i].id)
        }

        if (removeBackStack) {
            //navController.backQueue.last()
            super.onBackPressed()
        }
        navController.navigate(fragmentId, args)
    }

    fun navigationDirection(
        fragmentId: Int,
        args: Bundle? = null,
        enterAnim: Int = android.R.animator.fade_in,
        exitAnim: Int = android.R.animator.fade_out
    ) {
        navController.navigate(
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
        navController.navigate(fragmentId)
    }

    fun navigationDirection(
        fragmentId: NavDirections, enterAnim: Int = android.R.animator.fade_in,
        exitAnim: Int = android.R.animator.fade_out
    ) {
        navController.navigate(
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
                this,
                color
            )
        )
    }

    fun setViewBackgroundDrawable(editText: EditText, drawable: Int) {
        editText.background =
            ContextCompat.getDrawable(this, drawable)
    }

    fun startNewActivity(intent: Intent, isFinish: Boolean = false, isAnim: Boolean = true) {
        startActivity(intent)
        if (isFinish) {
            finish()
        }
        if (BuildConfig.EnableAnim && isAnim) {
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }


}