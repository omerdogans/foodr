package com.example.foodr.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodr.data.repo.FoodDaoRepository

class FoodDetailViewModel : ViewModel() {

    val foodDao = FoodDaoRepository()

    fun foodSave(yemek_adi: String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) {
        foodDao.foodSave(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

}