package com.example.films_otus

import java.io.Serializable

data class FilmItem(val name: String, val image: Int, var isFavorite: Boolean)  : Serializable