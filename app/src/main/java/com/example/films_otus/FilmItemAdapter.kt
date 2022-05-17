package com.example.films_otus
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.films_otus.FilmData.item

class FilmItemAdapter(
    private val filmList: List<FilmItem>,
    private val listener: NewClickListener

): RecyclerView.Adapter<FilmItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmItemViewHolder(view)

    }

    override fun getItemCount(): Int {

        return filmList.size

    }

    interface NewClickListener {

        fun onDetailsClick(item: FilmItem, position: Int)
        fun onFavoriteClick(item: FilmItem, position: Int)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(filmList[position], listener)
       // holder.itemView.findViewById<ImageButton>(R.id.btFavor).setOnClickListener {
          //  item[position].isFavorite = !item[position].isFavorite

          //  if (!item[position].isFavorite) {
           //     item.removeAt(position)
          //  notifyItemRemoved(position)
           //  }

       // }
    }

}


