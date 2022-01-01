package com.minux.template_basic

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minux.template_basic.databinding.ItemCategoryBinding
import com.minux.template_basic.databinding.ItemRestaurantBinding

class CategoryRVAdapter(val context : Context) :
    RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    private val categorys = ArrayList<Category>()
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoryRVAdapter.ViewHolder {
        val binding : ItemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryRVAdapter.ViewHolder, position: Int) {
        holder.bind(categorys[position])
    }

    override fun getItemCount(): Int = categorys.size

    @SuppressLint("NotifyDayaSetChanged")
    fun addSongs(categorys : ArrayList<Category>)
    {
        this.categorys.clear()
        this.categorys.addAll(categorys)

        notifyDataSetChanged()
    }

    fun removeSong(position: Int)
    {
        categorys.removeAt(position)
        notifyDataSetChanged()
    }

    // ****************************현재 여기 부분 코드 binding 부분이 다른 binding인듯
    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(category : Category)
        {
            Glide.with(context)
                .load(category.imgUrl)
                .into(binding.itemCategoryIconIv)

            binding.itemCategoryTextTv.text = category.categoryName
        }
    }

}