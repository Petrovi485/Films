package com.example.films_otus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.films_otus.databinding.ActivityFavoriteBinding
import com.example.films_otus.databinding.ActivityMainBinding

class Favorite : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        //val adapter = FilmItemAdapter(FilmData.item.filter { it.isFavorite }.toMutableList(), FilmItemAdapter.NewClickListener)

    }

    private fun initRecycler() {


        binding.recyclerFavor.adapter = FilmItemAdapter(
            FilmData.item.filter { it.isFavorite }.toMutableList(),
            object : FilmItemAdapter.NewClickListener{
                override fun onDetailsClick(item: FilmItem, position: Int) {

                }

                override fun onFavoriteClick(item: FilmItem, position: Int) {

                    binding.recyclerFavor.adapter?.notifyItemRemoved(position)

                }

            }

        )

    }


}