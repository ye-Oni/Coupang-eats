package com.minux.template_basic

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("storeID")var storeID : Int = 0,
    @SerializedName("imgUrl")var imgUrl : String = "",
    @SerializedName("storeName")var storeName : String = "",
    @SerializedName("deliveryFee")var deliveryFee : Int = 0,
    @SerializedName("deliveryTime")var deliveryTime : String = "",
    @SerializedName("packTime")var packTime : String = "",
    @SerializedName("isCheetah")var isCheetah : Int = 0,
    @SerializedName("rate")var rate : Double = 0.0,
    @SerializedName("reviewNum")var reviewNum : Int = 0,
    @SerializedName("distance")var distance : Double = 0.0,
)
