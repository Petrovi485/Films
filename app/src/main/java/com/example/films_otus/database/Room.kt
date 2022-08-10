package com.example.films_otus.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {

    @Query("select * from databasefilm")
    fun getMovies(): LiveData<List<DatabaseFilm>>

    @Insert
    suspend fun insertMovies(items: List<DatabaseFilm>)
}

@Database(
    version = 1,
    entities = [DatabaseFilm::class]

)
abstract class AppDB: RoomDatabase() {

    abstract val mainDao: MainDao
}

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