package com.example.films_otus
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.films_otus.API.MainItem

class FilmItemViewHolder(item: View): RecyclerView.ViewHolder(item) {
    private val imageIm: ImageView = itemView.findViewById(R.id.ivImage)
     private var tvName: TextView = itemView.findViewById(R.id.tvName)
    private val btFavorite: ImageButton = itemView.findViewById(R.id.btFavor)
   private val bd1: CardView = itemView.findViewById(R.id.bd1)
    //lateinit var mCtx: Context



    fun bind(item: MainItem, listener: FilmItemAdapter.NewClickListener) {
        tvName.text = item.name

        Glide.with(imageIm.context)
            .load(item.posterUrlPreview)
            .fitCenter()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .into(imageIm)



       if (item.isFavorite) {
            btFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)


        } else {
           btFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }

        btFavorite.setOnClickListener{
            listener.onFavoriteClick(item, adapterPosition)

        }

        bd1.setOnClickListener{
            listener.onDetailsClick(item, adapterPosition)

        }
    }


}



