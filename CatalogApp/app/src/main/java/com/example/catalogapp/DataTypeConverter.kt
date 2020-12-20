package com.example.catalogapp

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

class DataTypeConverter {
    @TypeConverter
    fun fromListCarImg(value : List<CarImgAdapter>) = Json.encodeToJsonElement(value)
    @TypeConverter
    fun toListCarImg(value: JsonObject) = Json.decodeFromJsonElement<List<CarImgAdapter>>(value)
    @TypeConverter
    fun fromListInt(value: List<Int>) = Json.encodeToString(value)
    @TypeConverter
    fun toListInt(value: String) = Json.decodeFromString<List<Int>>(value)
}