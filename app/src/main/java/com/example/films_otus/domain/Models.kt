package com.example.films_otus.domain

import android.os.Parcelable
import java.io.Serializable


data class DevByteFilm(val nameRu: String,
                       val posterUrl: String,
                       var isFavorite: Boolean) : Serializable




