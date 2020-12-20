package com.example.catalogapp

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CarRepository (application: Application) : CoroutineScope {
    override  val coroutineContext : CoroutineContext
        get() = Dispatchers.Main

    private var carDao: CarDao
    private var allCars: LiveData<List<Car>>

    init {
        val database = CarDatabase.getInstance(application)
        carDao = database!!.carDao()
        loadCars()
        allCars = carDao.getAllCars()
    }

    private fun loadCars() {
        insert(Car(0,
            imgURLs = listOf(
                R.drawable.aston1,
                R.drawable.aston2,
                R.drawable.aston3,
                R.drawable.aston4,
                R.drawable.aston5,
                R.drawable.aston6,
                R.drawable.aston7,
                R.drawable.aston8,
                R.drawable.aston9,
                R.drawable.aston10,
                R.drawable.aston11,
                R.drawable.aston12,
            ),
            manufacture = "Aston Martin", model = "DB11",
            type = "Coupe", generation = "", productionYearFrom = 2016,
            productionYearTo = -1, engineCapacities = listOf(4000, 5200),
            maxSpeed = 322.0, timeTo100 = 3.8
        ))

        insert(Car(1,
            imgURLs = listOf(
                R.drawable.mercedes_klasa_g,
                R.drawable.mercedes_klasa_g,
                R.drawable.mercedes_klasa_g,
                R.drawable.mercedes_klasa_g,
                R.drawable.mercedes_klasa_g,
                R.drawable.mercedes_klasa_g
            ),
            manufacture = "Mercedes-Benz", model = "G Class",
            type = "SUV", generation = "W464", productionYearFrom = 2018,
            productionYearTo = -1, engineCapacities = listOf(4000, 5000),
            maxSpeed = 210.0, timeTo100 = 5.9
        ))
    }

    fun insert(car: Car){
        launch { insertCarBG(car) }
    }

    fun delete(car: Car){
        launch { deleteCarBG(car) }
    }

    fun deleteAllCars() {
        launch { deleteAllCarsBG() }
    }

    fun getAllCars(): LiveData<List<Car>> {
        allCars = carDao.getAllCars()
        return allCars
    }

    fun getCarsWithType(type: String): LiveData<List<Car>> {
        allCars = carDao.getCarsWithType(type)
        return allCars
    }

    private suspend fun insertCarBG(car: Car){
        withContext(Dispatchers.IO){
            carDao.insert(car)
        }
    }

    private suspend fun deleteCarBG(car: Car){
        withContext(Dispatchers.IO){
            carDao.delete(car)
        }
    }

    private suspend fun deleteAllCarsBG(){
        withContext(Dispatchers.IO){
            carDao.deleteAllObjects()
        }
    }
}