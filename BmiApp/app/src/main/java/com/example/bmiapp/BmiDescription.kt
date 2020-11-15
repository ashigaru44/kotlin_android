package com.example.bmiapp

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.annotation.RequiresApi

class BmiDescription : Activity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_description)
        val dm = DisplayMetrics()
        display?.getRealMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width*0.8).toInt(), (height*0.5).toInt())
        val bmiValue = intent.getStringExtra("bmi_value").toString()
        val bmiPopUp = findViewById<TextView>(R.id.bmiPopUp)
        bmiPopUp.text = bmiValue

    }
}
