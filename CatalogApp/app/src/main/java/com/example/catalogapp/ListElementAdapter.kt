package com.example.catalogapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class ViewH(
    inflater: LayoutInflater,
    parent: ViewGroup,
    itemView: View,
    onItemClickListener: ListElementAdapter.OnItemClickListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var imgView: ImageView? = null
    private var manufactureView: TextView? = null
    private var modelView: TextView? = null
    private var typeView: TextView? = null
    private var onItemClickListener: ListElementAdapter.OnItemClickListener

    init {
        imgView = itemView.findViewById(R.id.mainIMG)
        manufactureView = itemView.findViewById(R.id.manufactureTV)
        modelView = itemView.findViewById(R.id.modelTV)
        typeView = itemView.findViewById(R.id.typeTV)
        this.onItemClickListener = onItemClickListener
        itemView.setOnClickListener(this)
    }

    fun bind(car: Car) {
        imgView?.setImageResource(car.imgURLs[0].getImage())
        manufactureView?.text = car.manufacture
        modelView?.text = car.model
        typeView?.text = car.type
    }

    override fun onClick(v: View?) {
        onItemClickListener.onItemClick(adapterPosition)
    }

}

class ListElementAdapter(
    val data: LiveData<List<Car>>, private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ViewH>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        Log.d("dataVAL", "test")
        Log.d("dataVAL", data.toString())
        return ViewH(inflater, parent, itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
//        val car: Car = data.value!!.get(position)
        holder.bind(data.value?.get(position)!!)
    }

    override fun getItemCount(): Int = data.value!!.size


//    fun setItems(items : List<Car>) {
//        data.value.
//    }

    fun getItemAt(position: Int): Car {
        return data.value!![position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}


