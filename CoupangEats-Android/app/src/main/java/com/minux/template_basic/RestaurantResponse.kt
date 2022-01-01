package com.minux.template_basic

import com.google.gson.annotations.SerializedName

data class RestaurantResult(val restaurants : ArrayList<Restaurant>)

data class RestaurantResponse(
    @SerializedName("isSuccess")val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message")val message: String,
    @SerializedName("result")val result: ArrayList<Restaurant>
)