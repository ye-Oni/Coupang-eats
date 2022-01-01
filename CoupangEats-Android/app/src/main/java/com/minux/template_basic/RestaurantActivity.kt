package com.minux.template_basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minux.template_basic.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {
    lateinit var binding : ActivityRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}