package com.example.films_otus.repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.films_otus.database.AppDB2
import com.example.films_otus.database.DatabaseFilm
import com.example.films_otus.database.asDomainModel
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.network.App
import com.example.films_otus.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmRepository(private val database: AppDB2) {

        val films: LiveData<List<DevByteFilm>> = Transformations.map(database.mainDao.getMovies()) {
            it.asDomainModel()
        }

        suspend fun refreshVideos() {

            withContext(Dispatchers.IO) {

                val films = App.instance.apiMain.getFilms()

                database.mainDao.insertMovies(films.asDatabaseModel())
           }
        }

     suspend fun updateFilm(item: DevByteFilm) {

         withContext(Dispatchers.IO) {


             val model = DatabaseFilm(item.nameRu, item.posterUrl, item.isFavorite)

             database.mainDao.updateFilm(model)

         }
     }

    }
