package com.example.foodr.ui.adapter

import android.content.Context
import android.database.DatabaseUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodr.R
import com.example.foodr.data.entity.Basket
import com.example.foodr.databinding.FoodListBasketBinding
import com.example.foodr.ui.viewmodel.FoodBasketViewModel
import com.google.android.material.snackbar.Snackbar

class FoodBasketListAdapter(
    var mContext: Context,
    var foodBasketList: List<Basket>,
    var viewModel: FoodBasketViewModel
) : RecyclerView.Adapter<FoodBasketListAdapter.FoodListBasket>() {

    inner class FoodListBasket(var binding: FoodListBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListBasket {
        val binding: FoodListBasketBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.food_list_basket, parent, false
        )

        return FoodListBasket(binding)
    }

    override fun getItemCount(): Int {
        return foodBasketList.size
    }

    override fun onBindViewHolder(holder: FoodListBasket, position: Int) {
        val basket = foodBasketList.get(position)
        val basketHolder = holder.binding

        basketHolder.basketArticle = basket


        if (basket.kullanici_adi == "omer_dogan") {

        }


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(256, 256).into(basketHolder.ivFoodBasketPicture)

        basketHolder.ivBasketDelete.setOnClickListener {
            Snackbar.make(it, "${basket.sepet_yemek_id} - silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") {
                    remove(basket.sepet_yemek_id, basket.kullanici_adi)
                }.show()
        }
    }

    fun remove(sepet_yemek_id: Int, kullanici_adi: String) {
        viewModel.foodRemove(sepet_yemek_id, kullanici_adi)
    }

    fun foodUser(kullanici_adi: String){
        viewModel.foodUser(kullanici_adi)
    }


}