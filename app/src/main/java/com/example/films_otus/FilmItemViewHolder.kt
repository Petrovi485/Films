package com.example.films_otus
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmItemViewHolder(item: View): RecyclerView.ViewHolder(item) {
    private val imageIm: ImageView = itemView.findViewById(R.id.ivImage)
     private var tvName: TextView = itemView.findViewById(R.id.tvName)
    private val btFavor: ImageButton = itemView.findViewById(R.id.btFavor)
    private val bd1: Button = itemView.findViewById(R.id.bd1)


    fun bind(item: FilmItem, listener: FilmItemAdapter.NewClickListener) {
        imageIm.setImageResource(item.image)
        tvName.text = item.name

       if (item.isFavorite) {
            btFavor.setBackgroundResource(R.drawable.ic_baseline_favorite_24)

        } else {
           btFavor.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }






        btFavor.setOnClickListener{
            listener.onFavoriteClick(item, adapterPosition)

        }




        bd1.setOnClickListener{
            listener.onDetailsClick(item, adapterPosition)


        }
    }


}



