package com.example.foodr.retrofit

import com.example.foodr.data.entity.Answer
import com.example.foodr.data.entity.BasketAnswer
import com.example.foodr.data.entity.EatAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    //http://kasimadalan.pe.hu -> Base Url

    @GET("yemekler/tumYemekleriGetir.php")
    fun allFood(): Call<EatAnswer>

    @GET("yemekler/tumSepettekiYemekleriGetir.php")
    fun allFoodBasket(): Call<BasketAnswer>

    @GET("yemekler/tumSepettekiYemekleriGetir.php")
    fun allUserBasket(@Field("kullanici_adi") kullanici_adi:String): Call<BasketAnswer>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun foodAdd(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String): Call<Answer>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun foodRemove(
        @Field("sepet_yemek_id") sepet_yemek_id:Int,
        @Field("kullanici_adi") kullanici_adi:String
    ): Call<Answer>


}