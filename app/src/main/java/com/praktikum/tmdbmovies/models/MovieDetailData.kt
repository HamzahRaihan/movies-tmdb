package com.praktikum.tmdbmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailData(
    @SerializedName("imdb_id")
    val id : String?,

    @SerializedName("title")
    val title : String,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("poster_path")
    val poster : String?,

) : Parcelable {
    constructor(): this("" , "", "", "")
}