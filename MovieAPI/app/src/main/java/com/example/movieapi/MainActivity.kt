package com.example.movieapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter
    private var page = 1
    private var loadingMovies = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.setSingletonInstance(
            Picasso.Builder(this)
            .loggingEnabled(true)
            .build())

        RecyclerViewSetUp()
        LoadMovies()

        val recyclerView: RecyclerView = findViewById(R.id.MoviesContainer)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val childCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!loadingMovies && childCount + firstVisibleItemPosition >= itemCount && firstVisibleItemPosition >= 0) {
                    LoadMovies()
                }
            }
        })

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(movie: MovieInfo) {
                val intent = Intent(this@MainActivity, MovieDescription::class.java).apply {
                    putExtra("title", movie.title)
                    putExtra("overview", movie.overview)
                    putExtra("image", movie.image)
                }
                startActivity(intent)
            }
        })
    }



    private fun RecyclerViewSetUp() {
        val recyclerView: RecyclerView = findViewById(R.id.MoviesContainer)
        adapter = RecyclerAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun LoadMovies() {
        lifecycleScope.launch {
            try {
                val response: Response<MoviesResponse> = Movie.retrofitService.getMovies(API_KEY, page)

                if (response.body() != null) {
                    val movies = response.body()!!.results
                    adapter.addMovies(movies)
                    page++
                }
            }
            catch (e: Exception) {
                Log.e("MainActivity", "Error loading movies: ${e.message}")
            }
        }
        loadingMovies = false
    }
}