package com.example.films_otus.API

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class MainItem(
    val name: String,
    val posterUrlPreview: String,
    val posterUrl: String,
    var isFavorite: Boolean


): Serializable
{
@PrimaryKey(autoGenerate = true)

var id: Int = 0

}

