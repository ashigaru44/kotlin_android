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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        binding.bmiTV.text = bmiTV.toString()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (units.isEmpty()) {
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
                units = "METRIC"
                true
            }
            R.id.imperial_units -> {
                item.isChecked = !item.isChecked
                units = "IMPERIAL"
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

    fun calcBMI(view: View) {
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

        val bmiCal = BmiCalculate()
        bmiTV = bmiCal.calculateBMI(units, massET, heightET).toString()

        when {
            bmiTV.toDouble() < 16.0 -> binding.bmiTV.setTextColor(resources.getColor(R.color.dark_blue))
            bmiTV.toDouble() in 16.0..16.99 -> binding.bmiTV.setTextColor(resources.getColor(R.color.pale_blue))
            bmiTV.toDouble() in 17.0..18.49 -> binding.bmiTV.setTextColor(resources.getColor(R.color.dark_green))
            bmiTV.toDouble() in 18.5..24.99 -> binding.bmiTV.setTextColor(resources.getColor(R.color.green))
            bmiTV.toDouble() in 25.0..29.99 -> binding.bmiTV.setTextColor(resources.getColor(R.color.pale_yellow))
            bmiTV.toDouble() in 30.0..34.99 -> binding.bmiTV.setTextColor(resources.getColor(R.color.yellow))
            bmiTV.toDouble() in 35.0..39.99 -> binding.bmiTV.setTextColor(resources.getColor(R.color.red))
            bmiTV.toDouble() > 40.0 -> binding.bmiTV.setTextColor(resources.getColor(R.color.dark_red))
        }
        if (emptyFlag) {
            binding.bmiTV.text = bmiTV
            addData(bmiTV.toDouble(), massET.toDouble(), heightET.toDouble())
        }
    }

    fun openBMI(view: View) {
        val bmiIntent = Intent(this, BmiDescription::class.java)
        bmiIntent.putExtra("bmi_value", bmiTV)
        startActivity(bmiIntent)
    }

    fun addData(bmi: Double, mass: Double, height: Double) {
        val currentTime: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        var massUnits = ""
        var heightUnits = ""
        if (units == "METRIC") {
            massUnits = "kg"
            heightUnits = "cm"
        } else if (units == "IMPERIAL") {
            massUnits = "lb"
            heightUnits = "ft"
        }
        val item = ItemData(bmi, currentTime, mass, height, massUnits, heightUnits)

        while (items.size >= 10)
            items.removeFirst()
        items.add(item)
        saveData()
    }

}
