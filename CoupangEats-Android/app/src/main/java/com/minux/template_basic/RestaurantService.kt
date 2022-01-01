package com.minux.template_basic

import android.util.Log
import com.minux.template_basic.ApplicationClass.Companion.retrofit
import com.minux.template_basic.ui.main.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantService {
    private lateinit var restaurantView: RestaurantView

    fun setRestaurantView(restaurantView: RestaurantView)
    {
        this.restaurantView = restaurantView
    }

    fun getRestaurants()
    {
        val restaurantService = retrofit.create(RestaurantRetrofitInterface :: class.java)

        restaurantView.onGetRestaurantsLoading()

        restaurantService.getRestaurants().enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(call: Call<RestaurantResponse>, response: Response<RestaurantResponse>) {
                Log.d("SONGSERVE/API-RESPONSE", response.toString())

                val resp = response.body()!!

                Log.d("SONGSERVE/API-FLO", resp.toString())

                when(resp.code){
                    1000 -> restaurantView.onGetRestaurantsSuccess(resp.result)
                    else -> restaurantView.onGetRestaurantsFailure(resp.code, resp.message)
                }

            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                Log.d("REST/API-ERROR", t.toString())
                restaurantView.onGetRestaurantsFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

}