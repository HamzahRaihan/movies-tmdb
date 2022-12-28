package com.praktikum.tmdbmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieResponse
import com.praktikum.tmdbmovies.services.MovieApiInterface
import com.praktikum.tmdbmovies.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apikey = "54e056562f9604d3a1437ea513c7d945"
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList(apikey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        } )
    }
}