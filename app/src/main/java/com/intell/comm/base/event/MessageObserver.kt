package com.intell.comm.base.event

import androidx.annotation.StringRes

interface MessageObserver {
    fun onMessageReceived(@StringRes msgResId: Int)
    fun onMessageReceived(msgResId: String?)
}