package com.minux.template_basic.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.minux.template_basic.*
import com.minux.template_basic.databinding.FragmentHomeBinding
import com.minux.template_basic.ui.BaseFragment
import com.minux.template_basic.ui.main.MainActivity

class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), RestaurantView, CategoryView {

    private lateinit var restaurantRVAdapter: RestaurantRVAdapter
    private lateinit var categoryRVAdapter: CategoryRVAdapter

    override fun initAfterBinding() {



        val bannerAdapter = BannerViewpagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.coupangeats_banner2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.coupangeats_banner3))
        bannerAdapter.addFragment(BannerFragment(R.drawable.coupangeats_banner4))
        bannerAdapter.addFragment(BannerFragment(R.drawable.coupangeats_banner5))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun changeRestaurantActivity() {
        val intent = Intent(requireContext(), RestaurantActivity::class.java)

        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getRestaurants()
        getCategorys()
    }

    private fun getRestaurants()
    {
        val restaurantService = RestaurantService()
        restaurantService.setRestaurantView(this)
        restaurantService.getRestaurants()
    }

    private fun getCategorys()
    {
        val categoryService = CategoryService()
        categoryService.setCategoryView(this)
        categoryService.getCategorys()
    }

    private fun initRecyclerView()
    {
        restaurantRVAdapter = RestaurantRVAdapter(requireContext())
        binding.homePopularBrandRv.adapter = restaurantRVAdapter
        binding.homeNewRestaurantRv.adapter = restaurantRVAdapter

        restaurantRVAdapter.setMyItemClickListener(object : RestaurantRVAdapter.MyItemClickListener {
            override fun onItemClick() {
                Log.d("CLICK", "CLICK")
                changeRestaurantActivity()
            }
        })

        categoryRVAdapter = CategoryRVAdapter(requireContext())
        binding.homeCategoryRv.adapter = categoryRVAdapter
    }

    override fun onGetRestaurantsLoading() {
        binding.homeLoadingPb.visibility = View.VISIBLE
    }

    override fun onGetRestaurantsSuccess(restaurants: ArrayList<Restaurant>) {
        binding.homeLoadingPb.visibility = View.GONE
        restaurantRVAdapter.addSongs(restaurants)
    }

    override fun onGetRestaurantsFailure(code: Int, message: String) {
        binding.homeLoadingPb.visibility = View.GONE

        when(code){
            400 -> Log.d("LOOKFRAG/API-ERROR", message)
        }
    }



    override fun onGetCategorysLoading() {
        binding.homeLoadingPb.visibility = View.VISIBLE
    }

    override fun onGetCategorysSuccess(categorys: ArrayList<Category>) {
        binding.homeLoadingPb.visibility = View.GONE
        categoryRVAdapter.addSongs(categorys)
    }

    override fun onGetCategorysFailure(code: Int, message: String) {
        binding.homeLoadingPb.visibility = View.GONE

        when(code){
            400 -> Log.d("LOOKFRAG/API-ERROR", message)
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val bannerAdapter = BannerViewpagerAdapter(this)
//        bannerAdapter.addFragment(BannerFragment(R.drawable.coupangeats_banner))
//
//        binding.homeBannerVp.adapter = bannerAdapter
//        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//
//        return binding.root
//    }
}