package com.intell.comm.views.clientsModule

import com.intell.comm.base.viewModel.BaseViewModel

/**
 * this class use before home page view model
 * @see ClientsViewModel is work in background thread with the help of [kotlinx.coroutines.CoroutineScope]
 * using this class handle all live data in background thread.
 * @see ClientsViewModel handle loading, success and error state are below
 * */

class ClientsViewModel : BaseViewModel() {
    private val tag = "ClientsViewModel"
}