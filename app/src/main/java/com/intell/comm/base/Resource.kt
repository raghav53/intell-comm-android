package com.intell.comm.base

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(e: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, e)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(Status.EMPTY, data, null)
        }
    }
}
