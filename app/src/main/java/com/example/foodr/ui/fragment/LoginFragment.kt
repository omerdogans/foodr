package com.example.foodr.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.foodr.MainActivity
import com.example.foodr.R
import com.example.foodr.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)


        binding.btnNext.setOnClickListener {
            if (binding.kullaniciAdi.text.toString().isEmpty()) {
                binding.kullaniciAdi.error = "Bu alan boş bırakılamaz!"
            } else {
                val go = LoginFragmentDirections.loginListFragment()
                Navigation.findNavController(binding.root).navigate(go)
                var bundle = Bundle()
                bundle.putString("kullanici_adi",binding.kullaniciAdi.text.toString())

            }
        }


        return binding.root
    }


}