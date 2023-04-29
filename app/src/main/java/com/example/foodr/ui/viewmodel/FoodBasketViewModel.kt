package com.example.foodr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodr.data.entity.Basket
import com.example.foodr.data.repo.FoodDaoRepository

class FoodBasketViewModel: ViewModel() {

    val foodRepository = FoodDaoRepository()
    val foodBasketList : MutableLiveData<List<Basket>>

    init {

        foodBasket()
        foodBasketList = foodRepository.foodBasketBring()
    }

   fun foodBasket(){
       foodRepository.foodBasket()
   }

    fun foodRemove(sepet_yemek_id:Int,kullanici_adi:String){
        foodRepository.foodRemove(sepet_yemek_id,kullanici_adi)
    }

    fun foodUser(kullanici_adi: String){
        foodRepository.foodUser(kullanici_adi)
    }
}