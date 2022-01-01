package com.minux.template_basic

interface CategoryView {
    fun onGetCategorysLoading()
    fun onGetCategorysSuccess(categorys : ArrayList<Category>)
    fun onGetCategorysFailure(code : Int, message : String)
}