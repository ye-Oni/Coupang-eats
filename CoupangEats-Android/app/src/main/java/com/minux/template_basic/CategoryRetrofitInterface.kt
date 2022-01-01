package com.minux.template_basic

import retrofit2.Call
import retrofit2.http.GET

interface CategoryRetrofitInterface {
    @GET("stores/category")
    fun getCategorys() : Call<CategoryResponse>
}