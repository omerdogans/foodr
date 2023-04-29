package com.example.foodr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodr.data.entity.Food
import com.example.foodr.data.repo.FoodDaoRepository

class FoodListViewModel : ViewModel() {
    val foodRepository = FoodDaoRepository()
    val foodList :MutableLiveData<List<Food>>

    init {
        foodLoad()
        foodList = foodRepository.foodBring()
    }

    fun foodLoad(){
        foodRepository.foodLoad()
    }

}