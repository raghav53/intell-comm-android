package com.intell.comm.views.homeViews.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.intell.comm.base.Resource
import com.intell.comm.base.viewModel.BaseViewModel
import com.intell.comm.model.ApiResponse
import com.intell.comm.model.RegisterLoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class HomeViewModel : BaseViewModel() {
    private val tag = "HomeViewModel"

    private val registerLoginStateFlow: MutableStateFlow<Resource<ApiResponse<RegisterLoginModel>>> =
        MutableStateFlow(Resource.empty(null))
    val registerLoginDataStateFlow: StateFlow<Resource<ApiResponse<RegisterLoginModel>>> =
        registerLoginStateFlow

    fun registerAccountReset() {
        registerLoginStateFlow.value = (Resource.empty(null))
    }

    fun registerLoginAccount(map: HashMap<String, String>, isLogin: Boolean = true) =
        viewModelScope.launch {
            registerLoginStateFlow.value = Resource.loading(null)
            mainApiRepository.registerLoginAccount(isLogin, map)
                .catch { e ->
                    if (e is UnknownHostException || e is javax.net.ssl.SSLException || e is ConnectException) {
                        val noInternet = ApiResponse<RegisterLoginModel>()
                        noInternet.message = "No Internet Connection"
                        registerLoginStateFlow.value = (Resource.empty(noInternet))
                    } else {
                        registerLoginStateFlow.value = (Resource.error(e, null))
                    }
                    Log.e(tag, "registerLoginAccount: error")
                }.collect { data ->
                    Log.e(tag, "registerLoginAccount: " + data.message)
                    registerLoginStateFlow.value = (Resource.success(data))
                }
        }

}