package com.example.films_otus.network

import com.example.films_otus.database.DatabaseFilm
import com.example.films_otus.domain.DevByteFilm

data class NetworkFilmsContainer(
    val items: List<Item>
)

data class Item(
    val duration: Int,
    val kinopoiskId: Int,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val premiereRu: String,
    val year: Int,
    val isFavorite: Boolean,

    )

fun NetworkFilmsContainer.asDomainModel(): List<DevByteFilm> {
    return items.map {
        DevByteFilm(
            nameRu = it.nameRu,
            posterUrl = it.posterUrl,
           isFavorite = it.isFavorite)
    }
}


/**
 * Convert Network results to database objects
 */
fun NetworkFilmsContainer.asDatabaseModel(): List<DatabaseFilm> {
    return items.map {
        DatabaseFilm(
            nameRu = it.nameRu,
            posterUrl = it.posterUrl,
        isFavorite = it.isFavorite)
    }
}