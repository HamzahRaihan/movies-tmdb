//package com.praktikum.tmdbmovies.remote
//
//import com.praktikum.tmdbmovies.BuildConfig
//import com.praktikum.tmdbmovies.services.MovieApiInterface
//import com.praktikum.tmdbmovies.services.MovieApiService
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//
//object ApiConfig{
//    fun getApiService(): MovieApiInterface {
//        val loggingInterceptor =
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl()
//    }
//}