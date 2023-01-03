package com.praktikum.tmdbmovies

import android.content.Intent
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.praktikum.tmdbmovies.models.BaseResponse
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieViewParam
import com.praktikum.tmdbmovies.services.MovieApiInterface
import com.praktikum.tmdbmovies.services.MovieApiService
import com.praktikum.tmdbmovies.utils.Resource
import com.praktikum.tmdbmovies.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Hamzah Raihan Ikhsanul Fikri
// 2010631250052

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies_list.layoutManager = LinearLayoutManager(this)
        movies_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> -> movies_list.adapter = MovieAdapter(movies) }
        val button: Button = findViewById(R.id.btn_favorite)
        button.setOnClickListener{
            val intent = Intent( this, FavoriteMovieActivity::class.java)
            startActivity(intent)
        }
        observeData()
        initView()
    }

    private val viewModel: MovieViewModel by viewModels(
        factoryProducer = {
            ViewModelFactory.getInstance(application)
        }
    )

    private val adapter: MovieAdapter by lazy { MovieAdapter(listOf()) }

//    private fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.activity_main, container, false)
//    }

//    private fun onViewsCreated(view: View, savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
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
        viewModel.movies.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
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

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apikey = "54e056562f9604d3a1437ea513c7d945"
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieListRando(apikey).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                return callback(response.body()!!.results)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {

            }
        } )
    }


}