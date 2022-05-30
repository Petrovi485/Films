package com.example.films_otus.screens.main
import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.films_otus.FilmData
import com.example.films_otus.FilmItem
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.R
import com.example.films_otus.databinding.FragmentListBinding
import com.example.films_otus.screens.details.DetailsFragment

class ListFragment: Fragment() {

    var filmItemAdapter: FilmItemAdapter? = null

    lateinit var binding: FragmentListBinding

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
        initClickListener()
    }


private fun initClickListener() {

}

private fun initRecycler() {

   val filmlist = FilmData.filmlist as MutableList<FilmItem>
   filmItemAdapter = FilmItemAdapter(filmlist, newClickListener)
   binding.recycler.adapter = filmItemAdapter




}

private val newClickListener = object : FilmItemAdapter.NewClickListener {
   override fun onDetailsClick(item: FilmItem, position: Int) {



       parentFragmentManager.beginTransaction()
           .replace(R.id.frame_main, DetailsFragment())
           .addToBackStack(null)
           .commit()


   }

   override fun onFavoriteClick(item: FilmItem, position: Int) {
       FilmData.filmlist[position].isFavorite = !FilmData.filmlist[position].isFavorite
       binding.recycler.adapter?.notifyItemChanged(position)

   }
}



    companion object{
        fun clickMovie(filmItem: FilmItem){
            val bundle = Bundle()
            bundle.putSerializable("film", filmItem)

        }
    }


}