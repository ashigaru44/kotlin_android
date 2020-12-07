package com.example.catalogapp

import java.io.Serializable

class CarImgItem(var img : Int) : Serializable {
    fun getImage():  Int {
        return img
    }
}