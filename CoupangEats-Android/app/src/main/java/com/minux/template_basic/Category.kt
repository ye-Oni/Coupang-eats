package com.minux.template_basic

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("categoryID")var categoryID : Int = 0,
    @SerializedName("categoryName")var categoryName : String = "",
    @SerializedName("imgUrl")var imgUrl : String = "",
)
