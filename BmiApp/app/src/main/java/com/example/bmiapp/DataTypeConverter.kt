package com.example.bmiapp

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

class DataTypeConverter {
    @TypeConverter
    fun fromBmiItemToJson(value: BmiItem) = Json.encodeToJsonElement(value)
    @TypeConverter
    fun fromJsonToBmiItem(value: JsonObject) = Json.decodeFromJsonElement<BmiItem>(value)
}