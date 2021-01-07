package com.example.bmiapp

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class BmiRepository (application: Application): CoroutineScope {
    override  val coroutineContext : CoroutineContext
        get() = Dispatchers.Main

    private var bmiDao: BmiDao
    private var bmiRecords: LiveData<MutableList<BmiItem>>

    init {
        val database = BmiDatabase.getInstance(application)
        bmiDao = database!!.bmiDao()
        bmiRecords = bmiDao.getAllRecords()
    }

    fun insert(bmiItem: BmiItem) {
        launch { insertBG(bmiItem) }
    }

    fun getAllBmiRecords(): LiveData<MutableList<BmiItem>> {
        bmiRecords = bmiDao.getAllRecords()
        return bmiRecords
    }

    private suspend fun insertBG(bmiItem: BmiItem){
        withContext(Dispatchers.IO){
            bmiDao.insert(bmiItem)
        }
    }

}