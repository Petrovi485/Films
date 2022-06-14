package com.example.films_otus.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmData
import com.example.films_otus.FilmItem
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentFavoriteBinding
import com.example.films_otus.screens.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar

class FavoriteFragment: Fragment() {

    var filmItemAdapter: FilmItemAdapter? = null
    lateinit var binding: FragmentFavoriteBinding
    lateinit var listOfFavoriteFilms: MutableList<FilmItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater,  container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initClickListener()
    }


    private fun initClickListener() {

    }

    private fun initRecycler() {
        listOfFavoriteFilms = FilmData.filmlist.filter { it.isFavorite } as MutableList<FilmItem>
        filmItemAdapter = FilmItemAdapter(listOfFavoriteFilms, newClickListener)
        binding.recyclerfavorite.adapter = filmItemAdapter

    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener {
        override fun onDetailsClick(item: FilmItem, position: Int) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_main, DetailsFragment())
                .addToBackStack(null)
                .commit()

        }

        override fun onFavoriteClick(item: FilmItem, position: Int) {
            val positionInMainList = FilmData.filmlist.indexOf(item)
            FilmData.filmlist[positionInMainList].isFavorite = !FilmData.filmlist[positionInMainList].isFavorite
            filmItemAdapter?.deleteFavoriteItem(position)

            view?.let {
                Snackbar.make(it,
                    if (FilmData.filmlist[position].isFavorite) "Фильм ${FilmData.filmlist[position].name} добавлен в избранное"
                    else "Фильм ${FilmData.filmlist[position].name} удален из избранного", Snackbar.LENGTH_LONG)
                    .setAction("Отмена") {
                        FilmData.filmlist[positionInMainList].isFavorite = !FilmData.filmlist[positionInMainList].isFavorite
                        binding.recyclerfavorite.adapter?.notifyItemChanged(position)
                    }
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show()
            }


        }
    }
}