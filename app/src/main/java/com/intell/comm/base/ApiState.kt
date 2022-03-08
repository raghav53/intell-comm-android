package com.intell.comm.base
/**
 * [ApiState] is update API status class
 * [Loading] state show progressbar
 * [Failure] show message error type
 * [onSuccess] response is successful
 * [Empty] response is empty
 * */
sealed class ApiState {
    object Loading : ApiState()
    class Failure(val e: Throwable) : ApiState()
    class Success<T>(val data: T) : ApiState()
    object Empty : ApiState()
}
