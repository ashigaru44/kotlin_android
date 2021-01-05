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
                R.drawable.mercedes_g2,
                R.drawable.mercedes_g3,
                R.drawable.mercedes_g4,
                R.drawable.mercedes_g5,
                R.drawable.mercedes_g6,
                R.drawable.mercedes_g7,
                R.drawable.mercedes_g8,
                R.drawable.mercedes_g9,
            ),
            manufacture = "Mercedes - Benz", model = "G Class",
            type = "SUV", generation = "W464", productionYearFrom = 2018,
            productionYearTo = -1, engineCapacities = listOf(4000, 5000),
            maxSpeed = 210.0, timeTo100 = 5.9
        ))

        insert(Car(2,
            imgURLs = listOf(
                R.drawable.mustang2,
                R.drawable.mustang3,
                R.drawable.mustang1,
                R.drawable.mustang5,
                R.drawable.mustang6,
                R.drawable.mustang7,
                R.drawable.mustang8,
                R.drawable.mustang9,
                R.drawable.mustang10,
                R.drawable.mustang11,
            ),
            manufacture = "Ford", model = "Mustang",
            type = "Coupe", generation = "VI", productionYearFrom = 2014,
            productionYearTo = -1, engineCapacities = listOf(2300, 3700, 5000, 5200),
            maxSpeed = 250.0, timeTo100 = 4.3
        ))

        insert(Car(3,
            imgURLs = listOf(
                R.drawable.bmw5_1,
                R.drawable.bmw5_2,
                R.drawable.bmw5_3,
                R.drawable.bmw5_4,
                R.drawable.bmw5_5,
                R.drawable.bmw5_6,
                R.drawable.bmw5_7,
                R.drawable.bmw5_8,
                R.drawable.bmw5_9,
                R.drawable.bmw5_10,
                R.drawable.bmw5_11,
            ),
            manufacture = "BMW", model = "Series 5",
            type = "Sedan", generation = "G30", productionYearFrom = 2014,
            productionYearTo = -1, engineCapacities = listOf(2000, 2500, 3000),
            maxSpeed = 250.0, timeTo100 = 6.0
        ))

        insert(Car(4,
            imgURLs = listOf(
                R.drawable.bmwz4_0,
                R.drawable.bmwz4_1,
                R.drawable.bmwz4_2,
                R.drawable.bmwz4_3,
                R.drawable.bmwz4_4,
                R.drawable.bmwz4_5,
                R.drawable.bmwz4_6,
                R.drawable.bmwz4_7,
            ),
            manufacture = "BMW", model = "Z4",
            type = "Cabrio", generation = "III", productionYearFrom = 2018,
            productionYearTo = -1, engineCapacities = listOf(2000, 3000),
            maxSpeed = 250.0, timeTo100 = 6.2
        ))

        insert(Car(5,
            imgURLs = listOf(
                R.drawable.audi1,
                R.drawable.audi2,
                R.drawable.audi3,
                R.drawable.audi4,
                R.drawable.audi5,
                R.drawable.audi6,
                R.drawable.audi7,
            ),
            manufacture = "AUDI", model = "RS6",
            type = "Kombi", generation = "C7", productionYearFrom = 2013,
            productionYearTo = 2018, engineCapacities = listOf(4000),
            maxSpeed = 250.0, timeTo100 = 3.7
        ))

        insert(Car(6,
            imgURLs = listOf(
                R.drawable.gtr1,
                R.drawable.gtr2,
                R.drawable.gtr3,
                R.drawable.gtr4,
                R.drawable.gtr5,
                R.drawable.gtr6,
                R.drawable.gtr7,
                R.drawable.gtr8,
            ),
            manufacture = "Mercedes - Benz", model = "AMG GT-R",
            type = "Cabrio", generation = "W464", productionYearFrom = 2018,
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