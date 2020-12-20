package com.example.catalogapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ListElementAdapter.OnItemClickListener {
    private lateinit var carsType: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListElementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        adapter = ListElementAdapter(this)

        val model = ViewModelProvider(
            this,
            ListViewModelFactory(this.application)
        ).get(ListViewModel::class.java)
        model.getCarList().observe(this, { carsData ->
            adapter.setCarsData(carsData)
        })

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_options_menu, menu)
        return true
    }

//    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
//        if (carsType.isEmpty()) {
//            carsType = "ALL"
//        }
//        Log.d("TAGGGG", carsType)
//
//        if (carsType == "METRIC") {
//            menu.findItem(R.id.metric_carsType).isChecked = true
//        } else if (carsType == "IMPERIAL") {
//            menu.findItem(R.id.imperial_carsType).isChecked = true
//        }
//        return super.onPrepareOptionsMenu(menu)
//    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ItemDataActivity::class.java)
        intent.putExtra("carItem", adapter.getItemAt(position))
        startActivity(intent)
    }
}
