package com.praktikum.tmdbmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.praktikum.tmdbmovies.models.MovieDetailData
import com.praktikum.tmdbmovies.services.MovieApiInterface
import com.praktikum.tmdbmovies.services.MovieApiService
import kotlinx.android.synthetic.main.activity_detail_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    var extras:Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val extras = intent.extras
        val id = extras?.getString("imdb_id")
        val apikey = "54e056562f9604d3a1437ea513c7d945"
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)

        apiService.getMovieId(id,apikey).enqueue(object : Callback<MovieDetailData> {
            private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            override fun onResponse(call: Call<MovieDetailData>, response: Response<MovieDetailData>) {
                Glide.with(this@DetailMovieActivity).load( IMAGE_BASE + response.body()?.poster).into(poster_detail)
                movie_title.text = response.body()?.title
                overview.text = response.body()?.overview
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {

            }

        } )
    }
}