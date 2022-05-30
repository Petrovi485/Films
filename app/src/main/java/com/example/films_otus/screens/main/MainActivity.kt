package com.example.films_otus.screens.main

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.films_otus.FilmData
import com.example.films_otus.FilmItem
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.screens.favorite.FavoriteFragment
import com.example.films_otus.R
import com.example.films_otus.databinding.ActivityMainBinding
import com.example.films_otus.screens.details.DetailsFragment

class MainActivity : AppCompatActivity() {


   // var filmItemAdapter: FilmItemAdapter? = null

    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, ListFragment())
            .addToBackStack(null)
            .commit()


        //initClickListener()

        binding.like?.setOnClickListener{

            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_main, FavoriteFragment())
                .addToBackStack(null)
                .commit()

            binding.like!!.setVisibility(View.GONE)




        }




    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener {
        override fun onDetailsClick(item: FilmItem, position: Int) {



        }

        override fun onFavoriteClick(item: FilmItem, position: Int) {


        }
    }

    /*private fun initClickListener() {

    }

    override fun onResume() {
        super.onResume()
        initRecycler()
    }

    private fun initRecycler() {
        val filmlist = FilmData.filmlist as MutableList<FilmItem>
        filmItemAdapter = FilmItemAdapter(filmlist, newClickListener)
        binding.recycler.adapter = filmItemAdapter


        val divider = DividerItemDecoration(
            this, DividerItemDecoration
                .VERTICAL
        )
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)
            ?.let { divider.setDrawable(it) }
        binding.recycler.addItemDecoration(divider)

    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener{
        override fun onDetailsClick(item: FilmItem, position: Int) {

        }

        override fun onFavoriteClick(item: FilmItem, position: Int) {
            FilmData.filmlist[position].isFavorite = !FilmData.filmlist[position].isFavorite
            binding.recycler.adapter?.notifyItemChanged(position)

        }
    }





    fun ClickLike(view: View) {

        val intent = Intent(this@MainActivity, Favorite::class.java)
        startActivity(intent)

    }*/

    fun Click(view: View) {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Смотри какое я сделал приложение!");
        startActivity(Intent.createChooser(shareIntent, "Описание чегото"))

    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 1 ) {
            supportFragmentManager.popBackStack()
            binding.like?.setVisibility(View.VISIBLE)
           // var backstack = supportFragmentManager.backStackEntryCount
           // Log.d("Mylog", "$backstack")
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



  /*  fun favorite (view: View){

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, FavoriteFragment())
            .commit()

        btFav.setVisibility(View.INVISIBLE)








    }*/

}



