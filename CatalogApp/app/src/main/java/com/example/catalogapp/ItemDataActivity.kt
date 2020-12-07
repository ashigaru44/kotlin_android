package com.example.catalogapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class ItemDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_data_activity)

        val carImgsRecyclerView: RecyclerView = findViewById(R.id.carRecycleView)
        carImgsRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        Log.d("CLICK", "Activity_entered")
        val carItem = intent.getSerializableExtra("carItem") as Car
        Log.d("CLICK", carItem.toString())

        val manufactureTV: TextView = findViewById(R.id.item_manufactureTV)
        val modelTV: TextView = findViewById(R.id.item_modelTV)
        val generationTV: TextView = findViewById(R.id.generationTV)
        val yearFromTV: TextView = findViewById(R.id.yearFromTV)
        val yearToTV: TextView = findViewById(R.id.yearToTV)
        val engCapTV: TextView = findViewById(R.id.engCapTV)
        val maxSpeedTV: TextView = findViewById(R.id.maxSpeedTV)
        val timeTo100TV: TextView = findViewById(R.id.timeTo100TV)
        val carImgsItems = carItem.imgURLs

        carImgsRecyclerView.adapter = CarImgAdapter(carImgsItems)

        manufactureTV.text = carItem.manufacture
        modelTV.text = carItem.model
        generationTV.text = carItem.generation
        yearFromTV.text = carItem.productionYearFrom.toString()
        if (carItem.productionYearTo != -1)
            yearToTV.text = carItem.productionYearTo.toString()
        else
            yearToTV.text = "~"
        engCapTV.text = carItem.engineCapacities.joinToString()
        maxSpeedTV.text = carItem.maxSpeed.toString()
        timeTo100TV.text = carItem.timeTo100.toString()

    }
}