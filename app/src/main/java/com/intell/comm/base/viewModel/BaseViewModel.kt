package com.intell.comm.base.viewModel

import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intell.comm.base.MessageUtils
import com.intell.comm.base.Resource
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.event.SingleActionEvent
import com.intell.comm.base.event.SingleMessageEvent
import com.intell.comm.base.repository.MainApiRepository
import com.intell.comm.model.SimpleApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var mainApiRepository: MainApiRepository
    val messageUtils = MessageUtils()
    val baseBack: SingleActionEvent<Void> = SingleActionEvent()
    val baseClick: SingleActionEvent<View> = SingleActionEvent()
    val editTextValue: EditTextValueChangeEvent = EditTextValueChangeEvent()
    val normalMessage: SingleMessageEvent = SingleMessageEvent()
    val successMessage: SingleMessageEvent = SingleMessageEvent()
    val errorMessage: SingleMessageEvent = SingleMessageEvent()
    val infoMessage: SingleMessageEvent = SingleMessageEvent()
    val warnMessage: SingleMessageEvent = SingleMessageEvent()

    fun onBackClick() {
        baseBack.call()
    }

    fun onClick(v: View?) {
        baseClick.call(v)
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    /**
     * @see simpleApiResponsePost use for a API which response [SimpleApiResponse] model.
     * in this function use following api
     * */

    private val simpleStateFlow: MutableStateFlow<Resource<SimpleApiResponse>> =
        MutableStateFlow(Resource.empty(null))
    val simpleDataStateFlow: StateFlow<Resource<SimpleApiResponse>> = simpleStateFlow

    fun simpleApiResponsePostReset() {
        simpleStateFlow.value = (Resource.empty(null))
    }

    fun simpleApiResponsePost(url: String, map: Map<String, String>) = viewModelScope.launch {
        simpleStateFlow.value = Resource.loading(null)
        mainApiRepository.simpleApiResponsePost(url, map)
            .catch { e ->
                if (e is UnknownHostException || e is javax.net.ssl.SSLException || e is ConnectException) {
                    val noInternet = SimpleApiResponse()
                    noInternet.message = "No Internet Connection"
                    simpleStateFlow.value = (Resource.empty(noInternet))
                } else {
                    simpleStateFlow.value = (Resource.error(e, null))
                }
                Log.e("BaseViewModel", "simpleApiResponse: error")
            }.collect { data ->
                Log.e("BaseViewModel", "simpleApiResponse: " + data.message)
                simpleStateFlow.value = (Resource.success(data))
            }
    }

}