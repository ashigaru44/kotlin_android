package com.example.catalogapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class ListViewModel(application: Application) : ViewModel() {
    val repository: CarRepository = CarRepository(application)
    lateinit var carList: LiveData<List<Car>>

    internal fun getCarList(): LiveData<List<Car>> {
        loadData()
        return carList
    }

    fun insert(car: Car){
        repository.insert(car)
    }

    fun delete(car: Car){
        repository.delete(car)
    }

    fun deleteAll(car: Car){
        repository.deleteAllCars()
    }

    private fun loadData() {
        carList = repository.getAllCars()
        Log.d("dataCAR_LIST", carList.value.toString())
    }

}