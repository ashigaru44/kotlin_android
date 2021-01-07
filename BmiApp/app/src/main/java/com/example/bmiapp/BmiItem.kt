package com.example.bmiapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "bmi_table")
data class BmiItem(
    val bmi: Double,
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val mass: Double,
    val height: Double,
    val mass_units: String,
    val height_units: String
) : Serializable
