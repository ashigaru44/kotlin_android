package com.example.bmiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val exampleList: List<ItemData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item,
            parent, false
        )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.bmi.text = currentItem.bmi.toString()
        holder.date.text = currentItem.date
        holder.mass.text = currentItem.mass.toString()
        holder.height.text = currentItem.height.toString()
        holder.massUnits.text = currentItem.mass_units
        holder.heightUnits.text = currentItem.height_units

    }

    override fun getItemCount() = exampleList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bmi: TextView = itemView.findViewById(R.id.bmi)
        val date: TextView = itemView.findViewById(R.id.date_label)
        val mass: TextView = itemView.findViewById(R.id.mass_label)
        val height: TextView = itemView.findViewById(R.id.height_label)
        val massUnits: TextView = itemView.findViewById(R.id.mass_units_label)
        val heightUnits: TextView = itemView.findViewById(R.id.height_units_label)
    }


}