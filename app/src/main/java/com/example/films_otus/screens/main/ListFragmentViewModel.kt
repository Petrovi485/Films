package com.example.films_otus.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.films_otus.database.AppDataBase.getInstance
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.domain.PostFire
import com.example.films_otus.domain.PostItem
import com.example.films_otus.network.App
import com.example.films_otus.repository.FilmRepository
import kotlinx.coroutines.launch
import java.io.IOException

class ListFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val filmRepository = FilmRepository(getInstance(application)!!)
    val films = filmRepository.films

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        Log.d ("Mylog", "init_ok")
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                filmRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                networkError.printStackTrace()
                if(films.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun postFire(){
        viewModelScope.launch {

            App.instance.apiFire.postNot(
                PostItem(to = "edNpbXZjQsey68iII6Juxd:APA91bFovCKTAkdW38cis5uv2l4JeK8dQMI2CLzcXFGZrddwgLFvSW4QGMmzz88JhRPpWAnpHs-_HIEruV8rHTvhS_YQ9wyL5zZlLBaFC_HkRyCkobngMRMB5jgZVVpslY2jG2rIwHNo",
                PostFire(
                    body = "Купи лучшую водку у меня, не пожалееешь, бла-бла-бла",
                    title= "Привет, Алкаш"

            )
            ))


        }

    }

      fun updateFilm(item: DevByteFilm, position: Int){
          viewModelScope.launch {

              filmRepository.updateFilm(item)
          }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}