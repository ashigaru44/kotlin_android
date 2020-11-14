package com.example.bmiapp.databinding

import android.widget.EditText
import android.widget.TextView

class AppData {
    lateinit var bmiTV : TextView
    lateinit var heightET : EditText
    lateinit var massET : EditText
    var mass = 0.0
    var height = 0.0
}