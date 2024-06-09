package com.example.movieapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MovieDescription : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val image = intent.getStringExtra("image")

        val imageView: ImageView = findViewById(R.id.MoviePoster)
        val titleText: TextView = findViewById(R.id.Title)
        val overviewText: TextView = findViewById(R.id.Description)
        val returnButton: Button = findViewById(R.id.Return)

        titleText.text = title
        overviewText.text = overview

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/$image")
            .into(imageView)

        returnButton.setOnClickListener {
            finish()
        }
    }
}