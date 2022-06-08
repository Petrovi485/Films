package com.example.films_otus

import android.os.Parcelable
import java.io.Serializable

data class FilmItem(val name: String,
                    val image: Int,
                    var isFavorite: Boolean,
                    val title: Int)  : Serializable