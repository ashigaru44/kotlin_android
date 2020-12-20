package com.example.catalogapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListElementAdapter(
  private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ListElementAdapter.ViewH>() {

    lateinit var listener: OnItemClickListener
    private var carsData = listOf<Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        Log.d("dataVAL", "test")
        return ViewH(inflater, parent, itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
        holder.bind(carsData[position])
    }

    override fun getItemCount(): Int = carsData.size

    fun setCarsData(items : List<Car>) {
        this.carsData = items
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Car {
        return carsData[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


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
            imgView?.setImageResource(car.imgURLs[0])
            manufactureView?.text = car.manufacture
            modelView?.text = car.model
            typeView?.text = car.type
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

    }
}


