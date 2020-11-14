package com.example.bmiapp

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HistoryFragment : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_history)

//        val example_list = generateDummyList(100)
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
//        recyclerView.adapter = RecyclerViewAdapter(example_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
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