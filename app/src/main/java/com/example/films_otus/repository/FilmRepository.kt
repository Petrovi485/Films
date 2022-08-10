package com.example.films_otus.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.films_otus.database.AppDB
import com.example.films_otus.database.asDomainModel
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.network.App
import com.example.films_otus.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FilmRepository(private val database: AppDB) {

        val films: LiveData<List<DevByteFilm>> = Transformations.map(database.mainDao.getMovies()) {
            it.asDomainModel()
        }


        suspend fun refreshVideos() {

            withContext(Dispatchers.IO) {
                val films = App.instance.api.getFilms()
                database.mainDao.insertMovies(films.asDatabaseModel())
            }
        }

    }
