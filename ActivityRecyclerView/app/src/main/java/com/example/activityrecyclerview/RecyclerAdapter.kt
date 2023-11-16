package com.example.activityrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val mMovies : List<MainActivity.Movie>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage : ImageView = itemView.findViewById<ImageView>(R.id.imageView)
        val movieTitle : TextView = itemView.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.container, parent, false)

        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie : MainActivity.Movie = mMovies[position]
        holder.textView.text = movie.title
    }
}