package com.example.films_otus.API
import retrofit2.http.GET
import retrofit2.Call




interface Api {
    @GET ("premieres?year=2022&month=JANUARY")

    fun getFilms(): Call<RetrofiFilmItem>
}