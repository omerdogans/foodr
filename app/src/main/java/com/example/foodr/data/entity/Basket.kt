package com.example.foodr.data.entity

import com.example.foodr.ui.adapter.FoodBasketListAdapter


class Basket(
    var sepet_yemek_id: Int,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: Int,
    var yemek_siparis_adet: Int,
    var kullanici_adi: String) :java.io.Serializable {



}