package com.example.bmiapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BmiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bmiItem: BmiItem)

    @Update
    fun update(bmiItem: BmiItem)

    @Delete
    fun delete(bmiItem: BmiItem)

    @Query("DELETE FROM bmi_table")
    fun deleteAllObjects()

    @Query("SELECT * FROM bmi_table")
    fun getAllRecords(): LiveData<MutableList<BmiItem>>
}