package com.example.catalogapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class CarImgAdapter(
    private var carImgItems: List<Int>,
    private var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CarImgAdapter.CarViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.car_img_container,
                parent,
                false
            ),
            onItemClickListener
            )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.setCarImage(carImgItems[position])
    }

    override fun getItemCount(): Int = carImgItems.size

    class CarViewHolder(itemView: View, var onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var carImgView: RoundedImageView = itemView.findViewById(R.id.carContainer)

        init {
            itemView.setOnClickListener(this)
        }

        fun setCarImage(carImgItem: Int) {
            carImgView.setImageResource(carImgItem)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

    }

    fun getItemAt(position: Int): Int {
        return carImgItems[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}