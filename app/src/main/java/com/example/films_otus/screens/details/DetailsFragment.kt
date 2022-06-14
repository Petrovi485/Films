package com.example.films_otus.screens.details
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmData
import com.example.films_otus.FilmItem
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar

class DetailsFragment: Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var currentMovie: FilmItem
    var callBackDetails: CallBackDetails? = null

    override fun onAttach(context: Context) {

        if (context is CallBackDetails) callBackDetails = context


        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("film") as FilmItem

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.toolbar.title = currentMovie.name
            binding.ivDetail.setImageResource(currentMovie.image)
            binding.tvTitle.setText(currentMovie.title)
            setImageRes()

        binding.btFavorDetails.setOnClickListener {

            val bool = currentMovie.isFavorite
            currentMovie.isFavorite = !bool
            setImageRes()
            callBackDetails?.onFavoriteToggled(currentMovie)

            view.let {
                Snackbar.make(it,
                    if (currentMovie.isFavorite) "Фильм ${currentMovie.name} добавлен в избранное"
                    else "Фильм ${currentMovie.name} удален из избранного", Snackbar.LENGTH_LONG)


                    .setAction("Отмена") {
                        currentMovie.isFavorite = !currentMovie.isFavorite

                        setImageRes()

                    }
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show()
            }


        }

    }


    companion object{

        const val DETAIL_FRAGMENT_FILM_KEY = "film"

        fun newInstance(filmItem: FilmItem): Fragment {
            val args = Bundle()
            args.putSerializable(DETAIL_FRAGMENT_FILM_KEY, filmItem)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment

        }

    }

    private fun setImageRes(){

        if (currentMovie.isFavorite) {
            binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)}
        else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}

    }

}

interface CallBackDetails {
 fun  onFavoriteToggled (filmitem: FilmItem)

}