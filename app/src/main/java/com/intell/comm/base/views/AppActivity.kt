package com.intell.comm.base.views

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.intell.comm.base.MessageUtils
import com.intell.comm.base.event.MessageObserver
import com.intell.comm.base.viewModel.BaseViewModel

abstract class AppActivity<B : ViewDataBinding, V : BaseViewModel>(
    @field:LayoutRes @param:LayoutRes val layoutRes: Int?,
    private val viewModelClass: Class<V>?,
    @field:IdRes @param:IdRes private val navHostIdRes: Int?
) : BaseActivity<B, V>(layoutRes, viewModelClass, navHostIdRes) {

    val messageUtils = MessageUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateMessage()
    }

    private fun onCreateMessage(){
        viewModel.infoMessage.observe(this, object : MessageObserver{
            override fun onMessageReceived(msgResId: Int) {
            }

            override fun onMessageReceived(msgResId: String?) {
                TODO("Not yet implemented")
            }

        })
    }
}