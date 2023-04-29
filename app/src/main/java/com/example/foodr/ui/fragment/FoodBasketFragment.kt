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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodr.R
import com.example.foodr.data.entity.Basket
import com.example.foodr.databinding.FragmentFoodBasketBinding
import com.example.foodr.ui.adapter.FoodBasketListAdapter
import com.example.foodr.ui.viewmodel.FoodBasketViewModel


class FoodBasketFragment : Fragment(){

    private lateinit var binding: FragmentFoodBasketBinding
    private lateinit var viewModel: FoodBasketViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_food_basket, container, false)
        binding.foodBasketFragment = this
        generalButton()

        binding.rvFoodBasket.layoutManager = LinearLayoutManager(requireContext())


        viewModel.foodBasketList.observe(viewLifecycleOwner){
            val basketList = FoodBasketListAdapter(requireContext(),it, viewModel)
            binding.basketAdapter = basketList

        }


        return binding.root
    }


    fun generalButton() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            back(binding.root)
        }

        binding.btnBackBasket.setOnClickListener {
            back(it)
        }

    }

    fun back(it: View){
        val go = FoodBasketFragmentDirections.foodBasketList()
        Navigation.findNavController(it).navigate(go)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val foodViewModels: FoodBasketViewModel by viewModels()
        viewModel = foodViewModels

    }

    override fun onResume() {
        super.onResume()
        viewModel.foodBasket()
    }



}

