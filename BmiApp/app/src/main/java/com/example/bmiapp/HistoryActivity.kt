package com.example.bmiapp

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
//        val example_list = generateDummyList(100)
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
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
//    private fun generateDummyList(size: Int): List<ItemData> {
//        val list = ArrayList<ItemData>()
//        for (i in 0 until size) {
//            val item = ItemData()
//        }
//        return list
//    }
//    companion object {
//        @JvmStatic fun newInstance() : HistoryFragment {
//            return HistoryFragment()
//        }
//    }
}