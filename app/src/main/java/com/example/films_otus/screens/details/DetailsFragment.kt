package com.example.films_otus.screens.details
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmItem
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentDetailsBinding
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

        if (currentMovie.isFavorite) {
           binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)}
        else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}

        binding.btFavorDetails.setOnClickListener {

            val bool = currentMovie.isFavorite
            currentMovie.isFavorite = !bool

            if (currentMovie.isFavorite) {
                binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)}
            else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}

            callBackDetails?.onFavoriteToggled(currentMovie)



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

}

interface CallBackDetails {
 fun  onFavoriteToggled (filmitem: FilmItem)

}