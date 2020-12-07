package com.example.catalogapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    var carList: MutableLiveData<List<Car>>? = null

    internal fun getCarList(): MutableLiveData<List<Car>> {
        if (carList == null) {
            carList = MutableLiveData()
            loadData()
        }

        return carList as MutableLiveData<List<Car>>
    }

    private fun loadData() {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val carList = ArrayList<Car>()
            carList.add(
                Car(
                    imgURLs = listOf(
                        CarImgItem(R.drawable.mercedes_klasa_g),
                        CarImgItem(R.drawable.mercedes_klasa_g),
                        CarImgItem(R.drawable.mercedes_klasa_g),
                        CarImgItem(R.drawable.mercedes_klasa_g),
                        CarImgItem(R.drawable.mercedes_klasa_g),
                        CarImgItem(R.drawable.mercedes_klasa_g)
                    ),
                    manufacture = "Mercedes-Benz", model = "G Class",
                    type = "SUV", generation = "W464", productionYearFrom = 2018,
                    productionYearTo = -1, engineCapacities = listOf(4000, 5000),
                    maxSpeed = 210.0, timeTo100 = 5.9
                )
            )
            carList.add(
                Car(
                    imgURLs = listOf(
                        CarImgItem(R.drawable.aston1),
                        CarImgItem(R.drawable.aston2),
                        CarImgItem(R.drawable.aston3),
                        CarImgItem(R.drawable.aston4),
                        CarImgItem(R.drawable.aston5),
                        CarImgItem(R.drawable.aston6),
                        CarImgItem(R.drawable.aston7),
                        CarImgItem(R.drawable.aston8),
                        CarImgItem(R.drawable.aston9),
                        CarImgItem(R.drawable.aston10),
                        CarImgItem(R.drawable.aston11),
                        CarImgItem(R.drawable.aston12),
                    ),
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