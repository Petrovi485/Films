package com.example.films_otus

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.films_otus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var filmItemAdapter: FilmItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initClickListener()

    }

    private fun initClickListener() {

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



    fun Click(view: View) {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Смотри какое я сделал приложение!");
        startActivity(Intent.createChooser(shareIntent, "Описание чегото"))

    }

    fun ClickLike(view: View) {

        val intent = Intent(this@MainActivity, Favorite::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Привет")
            .setMessage("Вы уверены что хотите выйти?")
            .setNegativeButton("Нет") { dialog, which -> }
            .setNeutralButton("Позже") { dialog, which -> }
            .setPositiveButton("Да") { dialog, which -> super.onBackPressed() }
            .create()
            .show()

    }

}



