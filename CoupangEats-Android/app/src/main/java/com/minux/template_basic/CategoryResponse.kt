package com.minux.template_basic

import com.google.gson.annotations.SerializedName

data class CategoryResult(val categorys : ArrayList<Category>)

data class CategoryResponse(
    @SerializedName("isSuccess")val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message")val message: String,
    @SerializedName("result")val result: ArrayList<Category>
)