package com.example.films_otus.screens.main

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.screens.favorite.FavoriteFragment
import com.example.films_otus.R
import com.example.films_otus.databinding.ActivityMainBinding
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.notification.PushService

//import com.example.films_otus.screens.details.CallBackDetails

class MainActivity : AppCompatActivity() /*CallBackDetails*/ {

    lateinit var binding: ActivityMainBinding
    lateinit var pushReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pushReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras
                extras?.keySet()?.firstOrNull { it == PushService.KEY_ACTION}?.let{ key ->
                    when (extras.getString(key)) {
                        PushService.ACTION_SHOW_MESSAGE -> {
                            extras.getString(PushService.KEY_MESAGGE)?.let{ message ->

                                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()



                            }
                        }

                        else -> Log.d ("Mylog", "No needed key found")
                    }



                }
            }

        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(PushService.INTENT_FILTER)


        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, ListFragment())
            .addToBackStack(null)
            .commit()

        binding.bottomNav?.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.like -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, FavoriteFragment(), "123")
                        .addToBackStack(null)
                        .commit()

                    binding.bottomNav!!.visibility = View.INVISIBLE

                }

                R.id.share -> {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Смотри какое я сделал приложение!");
                    startActivity(Intent.createChooser(shareIntent, "Описание чегото"))

                }
            }
            true

        }

    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener {
        override fun onDetailsClick(item: DevByteFilm, position: Int) {

        }
        override fun onFavoriteClick(item: DevByteFilm, position: Int) {

        }

        override fun onLateClick(item: DevByteFilm, position: Int) {

        }
    }


    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 1 ) {
            var backstack = supportFragmentManager.backStackEntryCount
            Log.d("Mylog", "$backstack")
            supportFragmentManager.popBackStack()
            binding.bottomNav!!.visibility = View.VISIBLE

        } else {

            AlertDialog.Builder(this)
                .setTitle("Привет")
                .setMessage("Вы уверены что хотите выйти?")
                .setNegativeButton("Нет") { dialog, which -> }
                .setNeutralButton("Позже") { dialog, which -> }
                .setPositiveButton("Да") { dialog, which -> finish() }
                .create()
                .show()
        }
    }

    //override fun onFavoriteToggled(filmitem: DevByteFilm) {
        //FilmData.filmlist.find {
       //     it.name == filmitem.name

        //}?.isFavorite = filmitem.isFavorite

  //  }

}



