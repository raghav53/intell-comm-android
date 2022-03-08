package com.intell.comm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterLoginModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("image")
    @Expose
    val image: String? = null

    @SerializedName("firstName")
    @Expose
    val firstName: String? = null

    @SerializedName("lastName")
    @Expose
    val lastName: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("mobile")
    @Expose
    val mobile: Int? = null

    @SerializedName("isVerify")
    @Expose
    val isVerify: Int? = null

    @SerializedName("fcmToken")
    @Expose
    val fcmToken: String? = null

    @SerializedName("isDelete")
    @Expose
    val isDelete: Int? = null

    @SerializedName("extraJsonData")
    @Expose
    val extraJsonData: String? = null
}