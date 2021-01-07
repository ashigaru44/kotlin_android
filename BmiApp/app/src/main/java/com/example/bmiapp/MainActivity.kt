package com.example.bmiapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bmiapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bmiTV: String

    //    private lateinit var repository : BmiRepository
    private lateinit var model: BmiViewModel
    private var units: String = ""
    private var sharedPrefs = "sharedPrefs"
    private var bmiRecords: MutableList<BmiItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
//        repository = BmiRepository(this.application)
        bmiTV = binding.bmiTV.text.toString()
        model = ViewModelProvider(
            this,
            BmiViewModelFactory(this.application)
        ).get(BmiViewModel::class.java)
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
                saveData()
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

    private fun saveData() {
        loadData()
        val sharedPrefs = getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(bmiRecords)
        editor.putString("bmi_results", json)
        editor.apply()
    }

    private fun loadData() {
        try {
            bmiRecords = (model.getBmiRecords()).value!!
        } catch (e: NullPointerException) {
        }
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
        val bmiItem = BmiItem(bmi, currentTime, mass, height, massUnits, heightUnits)
        bmiRecords.add(bmiItem)
        model.insert(bmiItem)
    }
}
