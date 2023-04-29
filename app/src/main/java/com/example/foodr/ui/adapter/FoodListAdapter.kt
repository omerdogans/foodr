package com.example.foodr.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodr.R
import com.example.foodr.data.entity.Food
import com.example.foodr.databinding.FoodListDesignBinding
import com.example.foodr.ui.fragment.FoodListFragmentDirections
import com.example.foodr.ui.viewmodel.FoodListViewModel

class FoodListAdapter(var mContext:Context, var foodList: List<Food>, var viewModel:FoodListViewModel) : RecyclerView.Adapter<FoodListAdapter.FoodListDesign>() {

    inner class FoodListDesign(var binding: FoodListDesignBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListDesign {
        val binding: FoodListDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.food_list_design,parent,false)



        return FoodListDesign(binding)
    }

    override fun getItemCount(): Int {
       return foodList.size
    }

    override fun onBindViewHolder(holder: FoodListDesign, position: Int) {
        val foods = foodList.get(position)
        val foodHolder = holder.binding


        foodHolder.foodArticle = foods

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${foods.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(256,256).into(foodHolder.ivFoodPicture)
        //foodHolder.tvFoodPrice.text = "${foods.yemek_fiyat}"
        Log.d("yemek adı adapter",foods.yemek_resim_adi)

        foodHolder.cvFoodDetail.setOnClickListener {
            var go = FoodListFragmentDirections.foodListDetail(foods = foods)
            Navigation.findNavController(it).navigate(go)
        }


    }


}