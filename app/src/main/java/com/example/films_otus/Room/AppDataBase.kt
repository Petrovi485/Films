package com.example.films_otus.Room

import android.content.Context
import androidx.room.Room

object AppDataBase {

    private var INSTANCE: AppDB? = null

    fun getInstance(context: Context): AppDB? {

        if (INSTANCE == null) {
           synchronized(AppDataBase){
               INSTANCE = Room.databaseBuilder(context,
                   AppDB::class.java,
                   "DATABASE")
                   .build()
           }

        }
        return INSTANCE
    }

    fun destroyInstance(){

        INSTANCE?.close()
        INSTANCE = null
    }
}