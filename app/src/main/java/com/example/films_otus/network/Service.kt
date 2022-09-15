package com.example.films_otus.network

import android.app.Application
import android.media.MediaPlayer
import android.util.Log
import com.example.films_otus.domain.PostFire
import com.example.films_otus.domain.PostItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import java.util.concurrent.TimeUnit



interface Api {
        @GET ("premieres?year=2022&month=JANUARY")

        suspend fun getFilms(): NetworkFilmsContainer
    }

interface ApiFire{
    @POST ("fcm/send")

    suspend fun postNot(@Body elementBody: PostItem)
}



class App: Application() {

    lateinit var apiMain: Api

    lateinit var apiFire: ApiFire

    override fun onCreate() {
        super.onCreate()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Mylog", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("Mylog", "Token IS -> $token")
        })



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

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiMain = retrofit.create(Api::class.java)


        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val clientFire = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "key=AAAAAj2IeBg:APA91bG4IDw2vwCjShiScuucDiUVJv_HvvIdbQ5nBsPZgr9dmzgeXxKd-dcSd8U1b4yeb2jXlcVKS90XoJZiYo1DKpl64OPJXkDewB--8tnJFXpkQX5X6X1hxVmxk2eMt6LW7DsEP3CO")

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

        val retrofitFire = Retrofit.Builder()
            .baseUrl(BASE_URL_FIRE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientFire)
            .build()

        apiFire = retrofitFire.create(ApiFire::class.java)

    }

    companion object {

        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"
        const val BASE_URL_FIRE = "https://fcm.googleapis.com"

        lateinit var instance: App
            private set


    }


}