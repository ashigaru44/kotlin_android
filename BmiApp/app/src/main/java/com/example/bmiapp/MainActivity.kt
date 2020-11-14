package com.example.bmiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.bmiapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bmiTV: String
    private var units: String = ""
    private var items = ArrayList<ItemData>()
    private var sharedPrefs = "sharedPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        bmiTV = binding.bmiTV.text.toString()
//        metric_item = R.id.metric_units.toString()
//        print("METRIC ITEM ===== ")
//        print(metric_item)
//        print(metric_units)
        Log.d("TAGONCREATE", units)
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("bmiStr", bmiTV)
        outState.putString("units", units)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bmiTV = savedInstanceState.getString("bmiStr").toString()
        units = savedInstanceState.getString("units").toString()
        binding.bmiTV.text = bmiTV
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (units.isEmpty()) {
            Log.d("TAGGGG", "GETS TO IF")
            units = "METRIC"
        }
        Log.d("TAGGGG", units)

        if (units == "METRIC") {
            menu.findItem(R.id.metric_units).isChecked = true
        } else if (units == "IMPERIAL") {
            menu.findItem(R.id.imperial_units).isChecked = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                startActivity(Intent(this, HistoryFragment::class.java))
                true
            }
            R.id.metric_units -> {
                item.isChecked = !item.isChecked
//                item.isChecked = false
                units = "METRIC"
                Log.d("TAGGGG", units.toString())
                true
            }
            R.id.imperial_units -> {
                item.isChecked = !item.isChecked
                units = "IMPERIAL"
//                item.isChecked = true
//                metric_units = false.toString()
//                Log.d("TAGGGG", metric_units)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun saveData() {
        val sharedPrefs = getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(items)
        editor.putString("bmi_results", json)
        editor.apply()
    }

    fun loadData() {
        val sharedPrefs = getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("bmi_results", "")
        val type = object : TypeToken<ArrayList<ItemData>>() {
        }.type

        if (json == null || json == "")
            items = ArrayList()
        else
            items = gson.fromJson(json, type)
    }


    fun countBMI(view: View) {
        val massET = binding.massET.text.toString()
        val heightET = binding.heightET.text.toString()
        var emptyFlag = true
        if (massET.isBlank()) {
            binding.massET.error = getString(R.string.mass_empty)
            emptyFlag = false
        }
        if (heightET.isBlank()) {
            binding.heightET.error = getString(R.string.height_empty)
            emptyFlag = false
        }
        var mass = 0.0
        var height = 0.0
        try {
            mass = massET.toDouble()
            height = heightET.toDouble()
        } catch (e: NumberFormatException) {
            println(e)
        }
        if (units == "METRIC") {
            height /= 100
            bmiTV = (round(mass / height.pow(2) * 100) / 100).toString()
        }
        if (units == "IMPERIAL") {
            bmiTV = (round(mass / height.pow(2) * 70300) / 100).toString()
        }
        binding.bmiTV.text = bmiTV
        addData(bmiTV.toDouble(), mass, height)
    }

    fun addData(bmi: Double, mass: Double, height: Double) {
        val currentTime: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        var massUnits = ""
        var heightUnits = ""
        if (units == "METRIC") {
            massUnits = "kg"
            heightUnits = "cm"
        }
        else if (units == "IMPERIAL"){
            massUnits = "lb"
            heightUnits = "ft"
        }
        val item: ItemData = ItemData(bmi, currentTime, mass, height, massUnits, heightUnits)

        while (items.size >= 10)
            items.removeFirst()
        items.add(item)
        saveData()
    }

}
