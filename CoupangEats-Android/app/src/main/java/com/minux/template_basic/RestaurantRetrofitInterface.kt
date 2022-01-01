package com.minux.template_basic

import retrofit2.Call
import retrofit2.http.GET

interface RestaurantRetrofitInterface {
    @GET("/stores")
    fun getRestaurants() : Call<RestaurantResponse>
}