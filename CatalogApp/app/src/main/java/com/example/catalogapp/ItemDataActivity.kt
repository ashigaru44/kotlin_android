package com.example.catalogapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ItemDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.item_data_activity)
        Log.d("CLICK", "Activity_entered")
        val carItem = intent.getSerializableExtra("carItem")
        Log.d("CLICK", carItem.toString())
    }
}