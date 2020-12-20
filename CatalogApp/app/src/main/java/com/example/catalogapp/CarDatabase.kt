package com.example.catalogapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Database(entities = [Car::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class CarDatabase : RoomDatabase() {
        
    abstract fun carDao(): CarDao

    companion object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main

        var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase? {
            if (INSTANCE == null) {
                synchronized(CarDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CarDatabase::class.java,
                        "carDB"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }

}
