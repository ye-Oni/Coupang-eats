package com.minux.template_basic.data.remote.auth

import com.minux.template_basic.data.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/users/sign-up")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/users/log-in")
    fun login(@Body user: User): Call<AuthResponse>

    @GET("/users/auto-login")
    fun autoLogin(): Call<AuthResponse>
}