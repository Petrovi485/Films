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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        initClickListener()

    }

    private fun initClickListener() {

    }

    private fun initRecycler() {

        binding.recycler.adapter = FilmItemAdapter(
            FilmData.item as MutableList<FilmItem>,
            object : FilmItemAdapter.NewClickListener{
                override fun onDetailsClick(item: FilmItem, position: Int) {

                }

                override fun onFavoriteClick(item: FilmItem, position: Int) {

                    FilmData.item[position].isFavorite = !FilmData.item[position].isFavorite

                    binding.recycler.adapter?.notifyItemChanged(position)

                }
            }
        )

        val divider = DividerItemDecoration(this, DividerItemDecoration
            .VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)?.let { divider.setDrawable(it) }
        binding.recycler.addItemDecoration(divider)

    }

    fun Click (view: View) {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Смотри какое я сделал приложение!");
        startActivity(Intent.createChooser(shareIntent, "Описание чегото"))

    }

    fun ClickLike (view: View) {

        val intent = Intent(this@MainActivity, Favorite::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Привет")
            .setMessage("Вы уверены что хотите выйти?")
            .setNegativeButton("Нет") {dialog, which -> }
            .setNeutralButton("Позже") {dialog, which -> }
            .setPositiveButton("Да") {dialog, which -> super.onBackPressed()}
            .create()
            .show()

    }

}

