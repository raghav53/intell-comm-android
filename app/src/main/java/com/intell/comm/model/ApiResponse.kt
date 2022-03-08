package com.intell.comm.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T> : SimpleApiResponse() {
    @SerializedName("body")
    val body: T? = null

    override fun toString(): String {
        return "ApiResponse{" +
                "data=" + body +
                "}"
    }
}