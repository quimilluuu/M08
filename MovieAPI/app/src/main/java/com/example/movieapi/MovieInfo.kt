package com.example.movieapi

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieInfo (
    @SerializedName("poster_path") val image : String?,
    @SerializedName("title") val title : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("overview") val overview : String
)