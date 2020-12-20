package com.example.catalogapp

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi

class ImageActivity : Activity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CLICK2", "Activity_entered")
        setContentView(R.layout.img_activity)
        val dm = DisplayMetrics()
        display?.getRealMetrics(dm)
        var screenWidth = dm.widthPixels
        val relLayout : RelativeLayout = findViewById(R.id.rel_layout)
        val imgItem = intent.getSerializableExtra("carImg") as Int
        val dims = BitmapFactory.Options()
        dims.inJustDecodeBounds = true
        relLayout.setBackgroundResource(imgItem)
        BitmapFactory.decodeResource(resources, imgItem, dims)
        var imgHeight = dims.outHeight
        val imgWidth = dims.outWidth
        if (imgWidth > screenWidth) {
            Log.d("CLICK2", "IF_entered")
            screenWidth = (screenWidth *0.9).toInt()
            val ratio = screenWidth / imgWidth.toDouble()
            Log.d("CLICK2", "ratio = $ratio")
            val tempHeight = imgHeight
            imgHeight = (tempHeight * ratio).toInt()
            window.setLayout(screenWidth, imgHeight)
            Log.d("CLICK2", "IF_END")
        }
        else
            window.setLayout(imgWidth, imgHeight)

    }
}