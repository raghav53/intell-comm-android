package com.intell.comm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class SimpleApiResponse {
    @SerializedName("status")
    @Expose
    var status: Int = 0

    @SerializedName("message")
    @Expose
    var message: String? = "Something went wrong."

    @SerializedName("name")
    @Expose
    var name: String = ""

    override fun toString(): String {
        return "SimpleApiResponse{" +
                "status=" + status +
                ", message=" + message + ", name=" + name +
                "}"
    }
}