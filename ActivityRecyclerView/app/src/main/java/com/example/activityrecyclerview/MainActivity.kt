package com.example.activityrecyclerview

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeRecyclerView()
    }


    private fun changeUpRecyclerView() {

        var newRecyclerView : RecyclerView = findViewById<RecyclerView>(R.id.recycleView)
        newRecyclerView.layoutManager = GridLayoutManager(this, 2)
        newRecyclerView.adapter = RecyclerAdapter(getMovies())
    }

    data class Movie(
        var image : String,
        var title : String,
    )

    fun getMovies() : MutableList<Movie> {
        var movies : MutableList<Movie> = ArrayList()
        movies.add(Movie(""))

        return movies
    }
}