package com.minux.template_basic.data.remote.auth

import com.google.gson.annotations.SerializedName

data class Auth(@SerializedName("userID") val userID: Int)

data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Auth?
)