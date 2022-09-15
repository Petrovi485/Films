package com.example.films_otus.screens.details
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentDetailsBinding
import com.example.films_otus.domain.DevByteFilm
import com.example.films_otus.notification.PushService
import com.example.films_otus.screens.main.ListFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment: Fragment() {

    lateinit var binding: FragmentDetailsBinding
   // lateinit var currentMovie: DevByteFilm
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


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val films = bundle.getSerializable("bundleKey") as DevByteFilm
            setView(films)
        }

        val viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)




    }

    fun setView(films: DevByteFilm) {

        val viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
        val _position = mutableListOf<DevByteFilm>()
        val position = _position.indexOf(films)

        binding.tvTitle.setText(films.nameRu)

        Glide.with(binding.ivDetail.context)
            .load(films.posterUrl)
            .fitCenter()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .into(binding.ivDetail)

        if (films.isFavorite) {
            binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)}
        else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}

        binding.btFavorDetails.setOnClickListener {

            val bool = films.isFavorite
            films.isFavorite = !bool
            viewModel.updateFilm(films, position)

            if (films.isFavorite) {
                binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)}
            else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}
            callBackDetails?.onFavoriteToggled(films)

            view.let {
                it?.let { it1 ->
                    Snackbar.make(
                        it1,
                        if (films.isFavorite) "Фильм ${films.nameRu} добавлен в избранное"
                        else "Фильм ${films.nameRu} удален из избранного", Snackbar.LENGTH_LONG)

                        .setAction("Отмена") {
                            films.isFavorite = !films.isFavorite
                            if (films.isFavorite) {
                                binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_24)} else {binding.btFavorDetails.setImageResource(R.drawable.ic_baseline_favorite_border_24)}
                        }
                        .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                        .show()
                }
            }
        }


    }


}
interface CallBackDetails {
 fun  onFavoriteToggled (filmitem: DevByteFilm)

}