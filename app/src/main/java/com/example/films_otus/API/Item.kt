package com.example.films_otus.API

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Item(
    val countries: List<Country>,
    val duration: Int,
    val genres: List<Genre>,
    val kinopoiskId: Int,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val premiereRu: String,
    val year: Int,
    val isFavorite: Boolean
)