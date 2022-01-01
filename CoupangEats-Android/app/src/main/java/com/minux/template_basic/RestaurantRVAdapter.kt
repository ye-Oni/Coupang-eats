package com.minux.template_basic

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minux.template_basic.databinding.ItemRestaurantBinding

class RestaurantRVAdapter(val context : Context) :
    RecyclerView.Adapter<RestaurantRVAdapter.ViewHolder>() {

    private val restaurants = ArrayList<Restaurant>()
    private var isSelect = false
    private var itemPosition = 0

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var myItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener)
    {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RestaurantRVAdapter.ViewHolder {
        val binding : ItemRestaurantBinding = ItemRestaurantBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantRVAdapter.ViewHolder, position: Int) {
        holder.bind(restaurants[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick()
        }
    }

    override fun getItemCount(): Int = restaurants.size

    @SuppressLint("NotifyDayaSetChanged")
    fun addSongs(songs : ArrayList<Restaurant>)
    {
        this.restaurants.clear()
        this.restaurants.addAll(songs)

        notifyDataSetChanged()
    }

    fun removeSong(position: Int)
    {
        restaurants.removeAt(position)
        notifyDataSetChanged()
    }

    // ****************************현재 여기 부분 코드 binding 부분이 다른 binding인듯
    inner class ViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(restaurant : Restaurant)
        {
            Glide.with(context)
                .load(restaurant.imgUrl)
                .into(binding.itemRestaurantCoverImgIv)

            binding.itemRestaurantName.text = restaurant.storeName
            binding.itemRestaurantReviewTv.text = restaurant.rate.toString() + "(" + restaurant.reviewNum.toString() + ")" + " · " +
                    restaurant.distance.toString() + "km"

            if(restaurant.deliveryFee == 0)  // 무료배달일 경우
            {
                binding.itemRestaurantDeliverMoneyTv.text = "무료배달"
            }
            else
            {
                binding.itemRestaurantDeliverMoneyTv.text = "배달비 " + restaurant.deliveryFee.toString() + "원"
            }
        }
    }

}