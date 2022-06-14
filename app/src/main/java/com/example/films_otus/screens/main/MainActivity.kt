package com.example.films_otus.screens.main

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.films_otus.FilmData
import com.example.films_otus.FilmItem
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.screens.favorite.FavoriteFragment
import com.example.films_otus.R
import com.example.films_otus.databinding.ActivityMainBinding
import com.example.films_otus.screens.details.CallBackDetails
import com.example.films_otus.screens.details.DetailsFragment

class MainActivity : AppCompatActivity(), CallBackDetails {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        override fun onDetailsClick(item: FilmItem, position: Int) {

        }

        override fun onFavoriteClick(item: FilmItem, position: Int) {

        }
    }


    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 1 ) {
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

    override fun onFavoriteToggled(filmitem: FilmItem) {
        FilmData.filmlist.find {
            it.name == filmitem.name

        }?.isFavorite = filmitem.isFavorite

    }

}



