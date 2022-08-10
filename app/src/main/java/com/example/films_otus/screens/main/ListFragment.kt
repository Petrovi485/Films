package com.example.films_otus.screens.main
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.films_otus.FilmItemAdapter
import com.example.films_otus.databinding.FragmentListBinding
import com.example.films_otus.domain.DevByteFilm


class ListFragment: Fragment() {
    lateinit var binding: FragmentListBinding




     lateinit var filmItemAdapter: FilmItemAdapter



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

        viewModel.films.observe(viewLifecycleOwner, Observer<List<DevByteFilm>> { films ->
            films?.apply {
                 filmItemAdapter = FilmItemAdapter()
                filmItemAdapter.film = films
            }
        })


        //val filmlist = MainItem as MutableList<MainItem>
        //val viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)

        binding.recycler.adapter = filmItemAdapter

        /*App.instance.api.getFilms().enqueue(object: Callback<RetrofiFilmItem>
        {
            override fun onResponse(
                call: Call<RetrofiFilmItem>,
                response: Response<RetrofiFilmItem>
            ) {


                films.clear()

                if (response.isSuccessful){

                    response.body()?.items?.forEach { model ->
                        films.add(MainItem(

                            model.nameRu,
                            model.posterUrlPreview,
                            model.posterUrl,
                            model.isFavorite
                        ))

                    }
                }
                filmItemAdapter!!.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<RetrofiFilmItem>, t: Throwable) {

                t.printStackTrace()

            }


        })*/



    }
}

/*class ListFragment: Fragment() {



    lateinit var binding: FragmentListBinding

     var films = mutableListOf<MainItem>()




    lateinit var filmItemAdapter: FilmItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
    }

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
    Log.d("Mylog", "Yes Data1")

   //val filmlist = MainItem as MutableList<MainItem>
    val viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
   filmItemAdapter = FilmItemAdapter(films, newClickListener)
   binding.recycler.adapter = filmItemAdapter

    App.instance.api.getFilms().enqueue(object: Callback<RetrofiFilmItem>
        {
            override fun onResponse(
                call: Call<RetrofiFilmItem>,
                response: Response<RetrofiFilmItem>
            ) {


                films.clear()

                if (response.isSuccessful){

                    response.body()?.items?.forEach { model ->
                        films.add(MainItem(

                            model.nameRu,
                            model.posterUrlPreview,
                            model.posterUrl,
                            model.isFavorite
                        ))

                    }
                }
                filmItemAdapter!!.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<RetrofiFilmItem>, t: Throwable) {

                t.printStackTrace()

            }


        })

    filmItemAdapter!!.notifyDataSetChanged()

}

private val newClickListener = object : FilmItemAdapter.NewClickListener {
   override fun onDetailsClick(item: MainItem, position: Int) {

       parentFragmentManager.beginTransaction()
           .replace(R.id.frame_main, DetailsFragment.newInstance(item))
           .addToBackStack(null)
           .commit()
   }

   override fun onFavoriteClick(item: MainItem, position: Int) {
       films[position].isFavorite = !films[position].isFavorite
       binding.recycler.adapter?.notifyItemChanged(position)



       view?.let {
           Snackbar.make(it,
               if (films[position].isFavorite) "Фильм ${films[position].name} добавлен в избранное"
               else "Фильм ${films[position].name} удален из избранного", Snackbar.LENGTH_LONG)
               .setAction("Отмена") {
                   films[position].isFavorite = !films[position].isFavorite
                   binding.recycler.adapter?.notifyItemChanged(position)
                                  }
               .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
               .show()
       }

   }
}

  //  companion object{
     //   fun clickMovie(filmItem: FilmItem){
     //       val bundle = Bundle()
      //      bundle.putSerializable("film", filmItem)

       // }
   // }


}*/


