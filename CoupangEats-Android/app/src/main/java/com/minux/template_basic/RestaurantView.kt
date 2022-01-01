package com.minux.template_basic

interface RestaurantView {
    fun onGetRestaurantsLoading()
    fun onGetRestaurantsSuccess(restaurants : ArrayList<Restaurant>)
    fun onGetRestaurantsFailure(code : Int, message : String)
}