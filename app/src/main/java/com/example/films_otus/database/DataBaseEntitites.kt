package com.example.films_otus.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.films_otus.domain.DevByteFilm
import java.io.Serializable

@Entity
data class DatabaseFilm constructor(
    @PrimaryKey
    val nameRu: String,
    val posterUrl: String,
    val isFavorite: Boolean = false
    )


fun List<DatabaseFilm>.asDomainModel(): List<DevByteFilm> {
    return map {
        DevByteFilm(
            nameRu = it.nameRu,
            posterUrl = it.posterUrl,
        isFavorite = it.isFavorite)
    }
}