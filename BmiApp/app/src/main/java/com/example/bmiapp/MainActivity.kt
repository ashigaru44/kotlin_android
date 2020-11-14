package com.example.bmiapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.bmiapp.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import kotlin.math.pow
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bmiTV: String
    private var units: String = ""
    var SHARED_PREFS = "sharedPrefs"
//    private var metric_box: MenuItem = getElement

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
        if (units.isEmpty()){
            Log.d("TAGGGG", "GETS TO IF")
            units = "METRIC"
        }
        Log.d("TAGGGG", units)

        if (units == "METRIC"){
            menu.findItem(R.id.metric_units).isChecked = true
        }
        else if (units == "IMPERIAL"){
            menu.findItem(R.id.imperial_units).isChecked = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                displayHistory()
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

    fun displayHistory() {
        startActivity(Intent(this, HistoryFragment::class.java))
    }

    fun saveData() {
        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        var editor = sharedPrefs.edit()
        editor.putFloat("MASS", binding.massET.text.toString().toFloat())
        editor.putFloat("HEIGHT", binding.heightET.text.toString().toFloat())
        editor.putFloat("RESULT", binding.bmiTV.text.toString().toFloat())
        editor.putString("UNITS", units)
    }

    fun loadData() {
        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

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
        if (units == "METRIC")
        {
            height /= 100
            bmiTV = (round(mass / height.pow(2) * 100) / 100).toString()
        }
        if (units == "IMPERIAL"){
            bmiTV = (round(mass / height.pow(2) * 70300) / 100).toString()
        }
        binding.bmiTV.text = bmiTV
    }

}
