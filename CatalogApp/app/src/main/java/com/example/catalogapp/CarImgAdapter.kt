package com.example.catalogapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class CarImgAdapter(
    private var carImgItems: List<CarImgItem>,
    private var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CarImgAdapter.CarViewHolder>() {

    lateinit var listener: CarImgAdapter.OnItemClickListener

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

//        holder.carImgView.setOnClickListener(View.OnClickListener {
//            fun onClick(view: View){
//                intent = Intent(context, )
//            }
//        })
    }

    override fun getItemCount(): Int = carImgItems.size

    class CarViewHolder(itemView: View, var onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var carImgView: RoundedImageView = itemView.findViewById(R.id.carContainer)

        init {
            itemView.setOnClickListener(this)
        }

        fun setCarImage(carImgItem: CarImgItem) {
            carImgView.setImageResource(carImgItem.getImage())
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

    }

    fun getItemAt(position: Int): CarImgItem {
        return carImgItems[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}