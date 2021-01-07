package com.example.bmiapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Database(entities = [BmiItem::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun bmiDao(): BmiDao

    companion object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main
        var INSTANCE: BmiDatabase? = null

        fun getInstance(context: Context): BmiDatabase? {
            if (INSTANCE == null) {
                synchronized(BmiDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BmiDatabase::class.java,
                        "bmi_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}