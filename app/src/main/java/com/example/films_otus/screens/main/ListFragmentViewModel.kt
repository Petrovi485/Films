package com.example.films_otus.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.films_otus.database.AppDataBase.getInstance
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
                Log.d ("Mylog", "OK")
                filmRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false


            } catch (networkError: IOException) {
                if(films.value.isNullOrEmpty())
                    _eventNetworkError.value = true
                Log.d ("Mylog", "${_eventNetworkError.value}" )
            }
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