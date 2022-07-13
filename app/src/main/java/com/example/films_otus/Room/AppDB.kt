package com.example.films_otus.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.films_otus.API.MainItem


@Database(

    entities = [MainItem::class],
    version = 1
)
abstract class AppDB: RoomDatabase() {

    abstract fun getMainDao(): MainDao
}