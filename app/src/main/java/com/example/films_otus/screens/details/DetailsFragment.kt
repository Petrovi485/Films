package com.example.films_otus.screens.details
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmItem
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentDetailsBinding
class DetailsFragment: Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var currentMovie: FilmItem

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
           binding.btFavorDetails.setBackgroundResource(R.drawable.ic_baseline_favorite_24)}
        else {binding.btFavorDetails.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)}

        binding.btFavorDetails.setOnClickListener {


            currentMovie?.isFavorite = binding.btFavorDetails.isSelected
            if (binding.btFavorDetails.isSelected) {
                binding.btFavorDetails.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.btFavorDetails.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
            }

            val result = Bundle()
            result.putSerializable("EXTRA_FILM", currentMovie)
            parentFragmentManager.setFragmentResult("detailsresult", result)
            super.onPause()
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