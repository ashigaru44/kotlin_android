package com.example.catalogapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "car_table")
data class Car(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
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
