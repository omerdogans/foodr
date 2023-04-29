package com.example.foodr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val value = intent.getStringExtra("kullanici_adi")
        Log.d("kullanici Adi : " ,value.toString())
    }
}