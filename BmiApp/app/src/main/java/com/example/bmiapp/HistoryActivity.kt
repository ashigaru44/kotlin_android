package com.example.bmiapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HistoryFragment : Activity() {
    private var items = ArrayList<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_history)
        loadData()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = RecyclerViewAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun loadData() {
        val sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("bmi_results", "")
        Log.d("Json", json.toString())
        val type = object : TypeToken<ArrayList<ItemData>>() {
        }.type

        if (json == null || json == "")
            items = ArrayList()
        else
            items = gson.fromJson(json, type)
    }
}