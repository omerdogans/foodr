package com.example.foodr.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodr.R
import com.example.foodr.databinding.FragmentFoodListBinding
import com.example.foodr.ui.adapter.FoodListAdapter
import com.example.foodr.ui.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {

    private lateinit var binding: FragmentFoodListBinding
    private lateinit var viewModel: FoodListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater ,R.layout.fragment_food_list, container, false)
        binding.foodListFragment = this

        val value = arguments?.getString("kullanici_adi").toString()
        Log.d("kullan",value)


        binding.rvFoodList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapter = FoodListAdapter(requireContext(),it,viewModel)
            binding.foodListAdapter = adapter
        }

        binding.ivFoodListShop.setOnClickListener {
            var go = FoodListFragmentDirections.foodListBasket()
            Navigation.findNavController(it).navigate(go)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val foodViewModels:FoodListViewModel by viewModels()
        viewModel = foodViewModels

    }

    override fun onResume() {
        super.onResume()
        viewModel.foodLoad()
    }


}