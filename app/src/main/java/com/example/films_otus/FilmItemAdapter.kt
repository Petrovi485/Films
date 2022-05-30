package com.example.films_otus
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.films_otus.screens.main.ListFragment


class FilmItemAdapter(
    private val filmList: MutableList<FilmItem>,
    private val listener: NewClickListener

): RecyclerView.Adapter<FilmItemViewHolder>() {

    fun deleteFavoriteItem(position: Int) {
        filmList.removeAt(position)
        notifyItemRemoved(position)
    }

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

    }

}


