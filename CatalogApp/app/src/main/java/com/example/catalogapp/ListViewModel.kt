package com.example.catalogapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class ListViewModel(application: Application) : ViewModel() {
    val repository: CarRepository = CarRepository(application)
    lateinit var carList: LiveData<List<Car>>

    internal fun getCarList(): LiveData<List<Car>> {
        carList = repository.getAllCars()
        return carList
    }

    internal fun getCarsWithType(type: String): LiveData<List<Car>> {
        carList = repository.getCarsWithType(type)
        return carList
    }

    fun insert(car: Car) {
        repository.insert(car)
    }

    fun delete(car: Car) {
        repository.delete(car)
    }

    fun deleteAll(car: Car) {
        repository.deleteAllCars()
    }


}