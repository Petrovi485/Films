package com.example.films_otus.screens.details
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmItem
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
        Log.d("MyLog", "$currentMovie")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



            binding.textView.text = currentMovie.name



    }



}