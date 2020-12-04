package com.example.catalogapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var data = MutableLiveData<List<Car>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



        val model = ViewModelProviders.of(this).get(ListViewModel::class.java)
        model.getCarList().observe(this, Observer<List<Car>>(){
            carList ->
            data.value = carList
            val adapter : ListElementAdapter = ListElementAdapter(data)
            recyclerView.adapter = adapter
            Toast.makeText(this, "on", Toast.LENGTH_SHORT).show()
        })

    }
}