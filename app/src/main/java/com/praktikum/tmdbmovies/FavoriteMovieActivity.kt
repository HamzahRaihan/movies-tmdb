package com.praktikum.tmdbmovies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieResponse
import com.praktikum.tmdbmovies.models.MovieViewParam
import com.praktikum.tmdbmovies.services.MovieApiInterface
import com.praktikum.tmdbmovies.services.MovieApiService
import com.praktikum.tmdbmovies.utils.Resource
import com.praktikum.tmdbmovies.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)
        movies_list.layoutManager = LinearLayoutManager(this)
        movies_list.setHasFixedSize(true)
//        val button: Button = findViewById(R.id.btn_favorite)
//        button.setOnClickListener{
//            val intent = Intent( this, FavoriteMovieActivity::class.java)
//            startActivity(intent)
//        }
        observeData()
        initView()
    }

    private val viewModel: MovieViewModel by viewModels(factoryProducer = {
        ViewModelFactory.getInstance(application)
    })


//    private val viewModel: MovieViewModel by viewModels(factoryProducer = {
//        ViewModelFactory.getInstance(FavoriteMovieFragment().application)
//    })

    private val adapter: MovieAdapter by lazy { MovieAdapter(arrayListOf()) }

//    private fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.activity_favorite_movie, container, false)
//    }

//    private fun onViewsCreated(view: View, savedInstanceState: Bundle?) {
//        observeData()
//        initView()
//    }

    override fun onDestroy() {
        super.onDestroy()
        movies_list.adapter = null
    }

    private fun initView() {
        movies_list.layoutManager = LinearLayoutManager(this)
        movies_list.setHasFixedSize(true)
        setListener()
    }

    private fun observeData() {
        viewModel.favoriteMovies.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    adapter.submitList(resource.data)
                    adapter.setOnClickFavoriteListener { movie ->
                        updateFavoriteMovie(movie)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setListener() {
        adapter.setOnClickFavoriteListener { movie ->
            updateFavoriteMovie(movie)
        }
    }

    private fun updateFavoriteMovie(movie: MovieViewParam) {
        if (movie.isFavorite) {
            viewModel.updateFavorite(false, movie.id)
        } else {
            viewModel.updateFavorite(true,movie.id)
        }
    }
//        LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

//    private fun getMovieData(callback: (List<MovieViewParam>) -> Unit){
//        val apikey = "54e056562f9604d3a1437ea513c7d945"
//        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
//        apiService.getMovieListResult(apikey).enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                return callback(response.body()!!.movies)
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//
//            }
//        } )
//    }
}