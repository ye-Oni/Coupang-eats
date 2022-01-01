package com.minux.template_basic.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("pwd") val password: String,
    @SerializedName("userName") val name: String,
    @SerializedName("phoneNum") val phoneNum : String,
)
