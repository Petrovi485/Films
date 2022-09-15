package com.example.films_otus.screens.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.films_otus.database.AppDataBase
import com.example.films_otus.repository.FilmRepository

class FavoriteFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val filmRepository = FilmRepository(AppDataBase.getInstance(application)!!)
    val films = filmRepository.films
}