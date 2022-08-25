package com.example.films_otus.network

import android.app.Application
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import com.example.films_otus.network.NetworkFilmsContainer as NetworkFilmsContainer1


interface Api {
        @GET ("premieres?year=2022&month=JANUARY")

        suspend fun getFilms(): NetworkFilmsContainer1
    }

class App: Application() {

    lateinit var api: Api

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-API-KEY", "e61e817f-cd1c-4df3-9348-b847f1e7bfd0")

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
        Log.d ("Mylog", "OKOKHTTP")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        Log.d ("Mylog", "OKRetrofit")

        api = retrofit.create(Api::class.java)
        Log.d ("Mylog", "OKAPI")


        //Executors.newSingleThreadExecutor().execute {
        //  Runnable {
        //    AppDataBase.getInstance(this)
//

        // }

        //}
    }

    companion object {

        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"

        lateinit var instance: App
            private set


    }


}