package com.example.bmiapp

import android.util.Log
import java.lang.NumberFormatException
import kotlin.math.pow
import kotlin.math.round


class BmiCalculate {

    fun calculateBMI(units: String, massET: String, heightET: String): Double {
        var mass = 0.0
        var height = 0.0
        var bmi = 0.0
        try {
            mass = massET.toDouble()
            height = heightET.toDouble()
        } catch (e: NumberFormatException) {
            println(e)
        }
        if (units == "METRIC") {
            height /= 100
            bmi = (round(mass / height.pow(2) * 100) / 100)
        }
        if (units == "IMPERIAL") {
            bmi = (round(mass / height.pow(2) * 70300) / 100)
        }
        return bmi
    }
}