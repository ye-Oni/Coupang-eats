package com.minux.template_basic

import android.util.Log
import com.minux.template_basic.ApplicationClass.Companion.retrofit
import com.minux.template_basic.ui.main.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryService {
    private lateinit var categoryView: CategoryView

    fun setCategoryView(categoryView: CategoryView)
    {
        this.categoryView = categoryView
    }

    fun getCategorys()
    {
        val categoryService = retrofit.create(CategoryRetrofitInterface :: class.java)

        categoryView.onGetCategorysLoading()

        categoryService.getCategorys().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                Log.d("SONGSERVE/API-RESPONSE", response.toString())

                val resp = response.body()!!

                Log.d("SONGSERVE/API-FLO", resp.toString())

                when(resp.code){
                    1000 -> categoryView.onGetCategorysSuccess(resp.result)
                    else -> categoryView.onGetCategorysFailure(resp.code, resp.message)
                }

            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d("REST/API-ERROR", t.toString())
                categoryView.onGetCategorysFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

}