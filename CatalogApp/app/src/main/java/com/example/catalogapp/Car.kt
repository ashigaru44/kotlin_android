package com.example.catalogapp

import java.io.Serializable

data class Car(
    val imgURLs: List<Int>,
    val manufacture: String,
    val model: String,
    val type: String,
    val generation: String,
    val productionYearFrom: Int,
    val productionYearTo: Int,
    val engineCapacities: List<Int>,
    val maxSpeed: Double,
    val timeTo100: Double
) : Serializable
