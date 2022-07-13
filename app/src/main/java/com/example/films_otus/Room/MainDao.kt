package com.example.films_otus.Room

import androidx.room.Dao
import androidx.room.Insert
import com.example.films_otus.API.MainItem

@Dao
interface MainDao {

    @Insert
    fun insert(mainItem: MainItem?)
}