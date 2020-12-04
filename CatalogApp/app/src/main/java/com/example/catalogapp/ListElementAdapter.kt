package com.example.catalogapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class ViewH(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var imgView: ImageView? = null
    private var manufactureView: TextView? = null
    private var modelView: TextView? = null
    private var typeView: TextView? = null

    init {
        imgView = itemView.findViewById(R.id.mainIMG)
        manufactureView = itemView.findViewById(R.id.manufactureTV)
        modelView = itemView.findViewById(R.id.modelTV)
        typeView = itemView.findViewById(R.id.typeTV)
    }

    fun bind(car: Car) {
        manufactureView?.text = car.manufacture
        modelView?.text = car.model
        typeView?.text = car.type
    }
}

class ListElementAdapter(val data: LiveData<List<Car>>) :
    RecyclerView.Adapter<ViewH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val inflater = LayoutInflater.from(parent.context)
        return ViewH(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
//        val car: Car = data.value!!.get(position)
        holder.bind(data.value?.get(position)!!)
    }

    override fun getItemCount(): Int = data.value!!.size

}