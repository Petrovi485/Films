package com.example.films_otus.screens.main
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentListBinding
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.screens.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar

class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
     private var filmItemAdapter: FilmItemAdapter? = null
    var films = mutableListOf<DevByteFilm>()
    val viewModel: ListFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater,  container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val viewModel: ListFragmentViewModel by lazy {
            val activity = requireNotNull(this.activity) {
            }
            ViewModelProvider(this, ListFragmentViewModel.Factory(activity.application))[ListFragmentViewModel::class.java]
        }
        filmItemAdapter = FilmItemAdapter(films, newClickListener)
        binding.recycler.adapter = filmItemAdapter

         viewModel.films.observe(viewLifecycleOwner) { films ->
            films?.apply {

                   filmItemAdapter?.film = films
                   Log.d ("Mylog", "ViewModel observe ${filmItemAdapter!!.film}")
            }
        }

    }

    private val newClickListener = object : FilmItemAdapter.NewClickListener {
        override fun onDetailsClick(item: DevByteFilm, position: Int) {

            val result = item

            setFragmentResult("requestKey", bundleOf("bundleKey" to result))

            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_main, DetailsFragment())
                .addToBackStack(null)
                .commit()
        }

        override fun onFavoriteClick(item: DevByteFilm, position: Int) {
            val filmses = filmItemAdapter!!.film

            filmses[position].isFavorite = !filmses[position].isFavorite
            viewModel.updateFilm(item, position)
            binding.recycler.adapter?.notifyItemChanged(position)


            view?.let {
                Snackbar.make(
                    it,
                    if (filmses[position].isFavorite) "Фильм ${filmses[position].nameRu} добавлен в избранное"
                    else "Фильм ${filmses[position].nameRu} удален из избранного",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Отмена") {
                        filmses[position].isFavorite = !filmses[position].isFavorite
                        binding.recycler.adapter?.notifyItemChanged(position)
                    }
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show()
            }

        }

        override fun onLateClick(item: DevByteFilm, position: Int) {

        viewModel.postFire()
        }

    }

    }


