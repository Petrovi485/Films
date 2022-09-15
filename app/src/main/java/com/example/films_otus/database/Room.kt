package com.example.films_otus.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.films_otus.domain.DevByteFilm

@Dao
interface MainDao {

    @Query("select * from databasefilm")
    fun getMovies(): LiveData<List<DatabaseFilm>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(items: List<DatabaseFilm>)

    @Update
    suspend fun updateFilm(item: DatabaseFilm)
}

@Database(
    version = 1,
    entities = [DatabaseFilm::class]

)
abstract class AppDB2: RoomDatabase() {

    abstract val mainDao: MainDao
}

object AppDataBase {

    var INSTANCE: AppDB2? = null

    fun getInstance(context: Context): AppDB2? {

        if (INSTANCE == null) {
            synchronized(AppDataBase){
                INSTANCE = Room.databaseBuilder(context,
                    AppDB2::class.java,
                    "FILM_DATABASE")
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