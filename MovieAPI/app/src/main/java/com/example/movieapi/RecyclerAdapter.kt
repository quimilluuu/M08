package com.example.movieapi

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

interface OnItemClickListener {
    fun onItemClick(movie: MovieInfo)
}

class RecyclerAdapter (private var moviesList:  MutableList<MovieInfo> = mutableListOf()) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addMovies(newMovies: List<MovieInfo>) {
        moviesList.addAll(newMovies)
        notifyDataSetChanged()
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.MovieImage)
        val movieTitle: TextView = itemView.findViewById(R.id.MovieTitle)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(moviesList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.movie, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieInfo = moviesList[position]

        val imageUrl = movie.image

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/$imageUrl")
            .into(holder.movieImage, object : Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "Image loaded successfully for movie: ${movie.title}")
                }

                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                    Log.e("Picasso", "Error loading image for movie: ${movie.title}", e)
                }
            })

        holder.movieTitle.text = movie.title
    }

}