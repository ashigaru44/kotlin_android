package com.example.catalogapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    var carList: MutableLiveData<List<Car>>? = null

    internal fun getCarList(): MutableLiveData<List<Car>> {
        Log.d("dataVAL", carList.toString())
        if (carList == null) {
            carList = MutableLiveData()
            loadData()
        }
        Log.d("dataVAL", carList.toString())

        return carList as MutableLiveData<List<Car>>
    }

    private fun loadData() {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val carList = ArrayList<Car>()
            carList.add(
                Car(
                    imgURLs = listOf(R.drawable.mercedes_klasa_g),
                    manufacture = "Mercedes-Benz", model = "G Class",
                    type = "SUV", generation = "W464", productionYearFrom = 2018,
                    productionYearTo = -1, engineCapacities = listOf(4000, 5000),
                    maxSpeed = 210.0, timeTo100 = 5.9
                )
            )
            carList.add(
                Car(
                    imgURLs = listOf(R.drawable.aston_martin),
                    manufacture = "Aston Martin", model = "DB11",
                    type = "Coupe", generation = "", productionYearFrom = 2016,
                    productionYearTo = -1, engineCapacities = listOf(4000, 5200),
                    maxSpeed = 322.0, timeTo100 = 3.8
                )
            )
            this.carList!!.setValue(carList)
        }
    }

}