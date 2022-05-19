package com.example.films_otus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.films_otus.databinding.ActivityFavoriteBinding



class Favorite : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteBinding
    var filmItemAdapter: FilmItemAdapter? = null
    lateinit var listOfFavoriteFilms: MutableList<FilmItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()



    }

    private fun initRecycler() {
        listOfFavoriteFilms = FilmData.filmlist.filter { it.isFavorite } as MutableList<FilmItem>
        filmItemAdapter = FilmItemAdapter(listOfFavoriteFilms, newClickListener)
        binding.recyclerFavor.adapter = filmItemAdapter

    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener {
        override fun onDetailsClick(item: FilmItem, position: Int) {

        }

        override fun onFavoriteClick(item: FilmItem, position: Int) {
            val positionInMainList = FilmData.filmlist.indexOf(item)
            FilmData.filmlist[positionInMainList].isFavorite = !FilmData.filmlist[positionInMainList].isFavorite
            filmItemAdapter?.deleteFavoriteItem(position)
        }
    }


}