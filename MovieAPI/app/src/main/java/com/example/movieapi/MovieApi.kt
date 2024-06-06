package com.example.movieapi

import com.google.gson.Gson
import kotlinx.serialization.SerialName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "2ddaca020a3e5a96a371a380f328582e"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val gsonInstance = Gson()

interface MovieApi {
    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>
}

data class MoviesResponse(
    val results: List<MovieInfo>,
    val page: Int,
    @SerialName("total_pages") val totalPages: Int
)

object Movie {
    val retrofitService: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}