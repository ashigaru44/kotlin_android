package com.example.catalogapp

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    var carList: MutableLiveData<List<Car>>? = null

    internal fun getCarList(): LiveData<List<Car>> {
        if (carList == null) {
            carList = MutableLiveData()
            loadData()
        }
        return carList as LiveData<List<Car>>
    }

    private fun loadData() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val carList = ArrayList<Car>()
            carList.add(
                Car(
                    manufacture = "Mercedes-Benz", model = "G",
                    type = "SUV", generation = "W464", productionYearFrom = 2018,
                    productionYearTo = -1, engineCapacities = listOf(4000, 5000),
                    maxSpeed = 210.0, timeTo100 = 5.9
                )
            )
            carList.add(
                Car(
                    manufacture = "Aston Martin", model = "DB11",
                    type = "Coupe", generation = "", productionYearFrom = 2016,
                    productionYearTo = -1, engineCapacities = listOf(4000, 5200),
                    maxSpeed = 322.0, timeTo100 = 3.8
                )
            )
            this.carList!!.postValue(carList)
        },1000)
    }

}