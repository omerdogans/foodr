package com.example.foodr.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.foodr.data.entity.*
import com.example.foodr.retrofit.ApiUtils
import com.example.foodr.retrofit.FoodDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class FoodDaoRepository {

    var foodList: MutableLiveData<List<Food>>
    var foodBasketList: MutableLiveData<List<Basket>>
    var foodDao : FoodDao

    init {
        foodDao = ApiUtils.getFoodDao()
        foodList = MutableLiveData()
        foodBasketList = MutableLiveData()
    }

    fun foodBring(): MutableLiveData<List<Food>>{
        return foodList
    }

    fun foodBasketBring(): MutableLiveData<List<Basket>>{
        return foodBasketList
    }


    fun foodLoad(){
        foodDao.allFood().enqueue(object : retrofit2.Callback<EatAnswer>{
            override fun onResponse(call: Call<EatAnswer>, response: Response<EatAnswer>) {
               val flist = response.body()!!.yemekler
                foodList.value = flist
            }

            override fun onFailure(call: Call<EatAnswer>, t: Throwable) {
            }
        })
    }

    fun foodSave(yemek_adi: String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        foodDao.foodAdd(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object : retrofit2.Callback<Answer>{
            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {}
            override fun onFailure(call: Call<Answer>, t: Throwable) {}
        })
    }

    fun foodBasket(){
        foodDao.allFoodBasket().enqueue(object : retrofit2.Callback<BasketAnswer>{
            override fun onResponse(call: Call<BasketAnswer>, response: Response<BasketAnswer>) {
                val basketList = response.body()!!.sepet_yemekler
                foodBasketList.value = basketList
            }
            override fun onFailure(call: Call<BasketAnswer>, t: Throwable) {}
        })
    }

    fun foodRemove(sepet_yemek_id: Int,kullanici_adi:String){
        foodDao.foodRemove(sepet_yemek_id,kullanici_adi).enqueue(object :Callback<Answer>{
            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                foodBasket()
            }
            override fun onFailure(call: Call<Answer>, t: Throwable) {}

        })
    }

    fun foodUser(kullanici_adi:String){
        foodDao.allUserBasket(kullanici_adi).enqueue(object :Callback<BasketAnswer>{
            override fun onResponse(call: Call<BasketAnswer>, response: Response<BasketAnswer>) {
                val basketList = response.body()!!.sepet_yemekler
                foodBasketList.value = basketList
            }
            override fun onFailure(call: Call<BasketAnswer>, t: Throwable) {
            }
        })
    }

}

