package com.example.catalogapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class CarImgAdapter(private var carImgItems: List<CarImgItem>) : RecyclerView.Adapter<CarImgAdapter.CarViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.car_img_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.setCarImage(carImgItems.get(position))
//        holder.carImgView.setOnClickListener(View.OnClickListener {
//            fun onClick(view: View){
//                intent = Intent(context, )
//            }
//        })
    }

    override fun getItemCount(): Int = carImgItems.size

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var carImgView: RoundedImageView = itemView.findViewById(R.id.carContainer)

        fun setCarImage(carImgItem: CarImgItem) {
            carImgView.setImageResource(carImgItem.getImage())
        }
    }
}