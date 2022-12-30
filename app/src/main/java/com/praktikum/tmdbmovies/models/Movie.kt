package com.praktikum.tmdbmovies.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id : Int = 0,

    @SerializedName("title")
    val title : String,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("poster_path")
    val poster : String,

    @SerializedName("release_date")
    val release : String,

    @SerializedName("is_favorite")
    val isFavorite : Boolean,
) : Parcelable {
    constructor(): this( 0 , "", "", "", "", "", false)
}