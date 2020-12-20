package com.example.catalogapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(car: Car)

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)

    @Query("DELETE FROM car_table")
    fun deleteAllObjects()

    @Query("SELECT * FROM car_table")
    fun getAllCars(): LiveData<List<Car>>
}