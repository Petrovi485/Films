package com.example.films_otus
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.films_otus.database.DatabaseFilm
import com.example.films_otus.domain.DevByteFilm


class FilmItemAdapter(
    var films: MutableList<DevByteFilm>,
    private var listener: NewClickListener

): RecyclerView.Adapter<FilmItemViewHolder>() {

    var film: List<DevByteFilm> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film_new, parent, false)
        return FilmItemViewHolder(view)

    }


    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(film[position], listener)

    }

    override fun getItemCount(): Int {

        return film.size

    }


    interface NewClickListener {

        fun onDetailsClick(item: DevByteFilm, position: Int)
        fun onFavoriteClick(item: DevByteFilm, position: Int)
        fun onLateClick(item: DevByteFilm, position: Int)

    }


}




/*class FilmItemAdapter(
    private var films: MutableList<MainItem>,
    private var listener: NewClickListener

): RecyclerView.Adapter<FilmItemViewHolder>() {

    fun deleteFavoriteItem(position: Int) {
        films.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film_new, parent, false)
        return FilmItemViewHolder(view)

    }

    override fun getItemCount(): Int {

        return films.size

    }



    interface NewClickListener {

        fun onDetailsClick(item: MainItem, position: Int)
        fun onFavoriteClick(item: MainItem, position: Int)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(films[position], listener)

    }

}*/


