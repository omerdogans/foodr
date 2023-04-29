package com.example.foodr.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodr.R
import com.example.foodr.databinding.FragmentFoodDetailsBinding
import com.example.foodr.ui.viewmodel.FoodDetailViewModel


class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var viewModel: FoodDetailViewModel
    var a: Int = 1
    var amountTotalPrice = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_details, container, false)
        binding.foodDetailsFragment = this
        generalButton()
        picture()




        return binding.root

    }

    fun generalButton() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            back(binding.root)
        }

        binding.ivDetailsBack.setOnClickListener {
            back(it)
        }
        binding.ivDetailShopping.setOnClickListener {
            shoppingBasket(it)
        }
        binding.ivbDetailAdd.setOnClickListener {
            a++
            binding.tvDetailAmount.text = a.toString()
        }

        val b = 1
        binding.ivbDetailRemove.setOnClickListener {
            if (binding.tvDetailAmount.text.toString() > b.toString()){
                a--
                binding.tvDetailAmount.text = a.toString()
            }}
    }

    fun back(it: View) {
        val goes = FoodDetailsFragmentDirections.foodDetailsList()
        Navigation.findNavController(it).navigate(goes)
    }

    fun shoppingBasket(it: View) {
        val go = FoodDetailsFragmentDirections.foodDetailBasket()
        Navigation.findNavController(it).navigate(go)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailViewModel: FoodDetailViewModel by viewModels()
        viewModel = detailViewModel

    }

    fun foodSave(yemek_adi: String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) {
        viewModel.foodSave(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


    fun picture(){

        val bundle: FoodDetailsFragmentArgs by navArgs()
        val foodBring = bundle.foods

        binding.foodArticle = foodBring

        viewModel.foodDao.foodBring().value.apply {
            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${foodBring.yemek_resim_adi}"
            Glide.with(binding.root).load(url).override(400, 400).into(binding.ivFoodDetailPicture)

        }

    }

    fun onButtonClick(yemek_adi: String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        var detailAmonutFood = binding.tvDetailAmount.text.toString()
        totalPrice(binding.tvDetailAmount.text.toString(), yemek_fiyat.toString())
        foodSave(yemek_adi,yemek_resim_adi,amountTotalPrice,detailAmonutFood.toInt(),kullanici_adi)

    }

    fun totalPrice(a :String ,b:String){
        val sayi1 = a.toInt()
        val sayi2 = b.toInt()
         amountTotalPrice = sayi1 * sayi2
    }

}