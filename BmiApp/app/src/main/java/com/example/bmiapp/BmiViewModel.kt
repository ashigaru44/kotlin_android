package com.example.bmiapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class BmiViewModel(application: Application) : ViewModel() {
    private val repository: BmiRepository = BmiRepository(application)
    lateinit var bmiRecords: LiveData<MutableList<BmiItem>>

    internal fun getBmiRecords(): LiveData<MutableList<BmiItem>> {
        bmiRecords = repository.getAllBmiRecords()
        return bmiRecords
    }

    fun insert(bmiItem: BmiItem) = repository.insert(bmiItem)
}