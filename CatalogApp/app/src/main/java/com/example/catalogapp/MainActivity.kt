package com.example.catalogapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ListElementAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListElementAdapter
    private lateinit var model: ListViewModel
    private lateinit var observer: Observer<List<Car>>
    private var carsType = "ALL"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        adapter = ListElementAdapter(this)

        model = ViewModelProvider(
            this,
            ListViewModelFactory(this.application)
        ).get(ListViewModel::class.java)

        observer = Observer { carsData ->
            adapter.setCarsData(carsData)
        }
        getCars()


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.getItemAt(viewHolder.adapterPosition)
                    Toast.makeText(this@MainActivity, "Item deleted", Toast.LENGTH_SHORT).show()
                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun getCars() {
        when (carsType) {
            "ALL" ->
                model.getCarList().observe(this, observer)
            "SUV" ->
                model.getCarsWithType("SUV").observe(this, observer)
            "SEDAN" ->
                model.getCarsWithType("SEDAN").observe(this, observer)
            "COUPE" ->
                model.getCarsWithType("COUPE").observe(this, observer)
            "CABRIO" ->
                model.getCarsWithType("CABRIO").observe(this, observer)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_options_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (carsType.isEmpty()) {
            carsType = "ALL"
        }
        Log.d("TAGGGG", carsType)

        when (carsType) {
            "ALL" ->
                menu.findItem(R.id.all_types).isChecked = true
            "SUV" ->
                menu.findItem(R.id.suvs_filter).isChecked = true
            "SEDAN" ->
                menu.findItem(R.id.sedan_filter).isChecked = true
            "COUPE" ->
                menu.findItem(R.id.coupe_filter).isChecked = true
            "CABRIO" ->
                menu.findItem(R.id.cabrio_filter).isChecked = true
        }
        Log.d("TAGGGG", carsType)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all_types -> {
                item.isChecked = !item.isChecked
                carsType = "ALL"
                getCars()
                true
            }
            R.id.suvs_filter -> {
                item.isChecked = !item.isChecked
                carsType = "SUV"
                getCars()
                true
            }
            R.id.sedan_filter -> {
                item.isChecked = !item.isChecked
                carsType = "SEDAN"
                getCars()
                true
            }
            R.id.coupe_filter -> {
                item.isChecked = !item.isChecked
                carsType = "COUPE"
                getCars()
                true
            }
            R.id.cabrio_filter -> {
                item.isChecked = !item.isChecked
                carsType = "CABRIO"
                getCars()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ItemDataActivity::class.java)
        intent.putExtra("carItem", adapter.getItemAt(position))
        startActivity(intent)
    }
}
