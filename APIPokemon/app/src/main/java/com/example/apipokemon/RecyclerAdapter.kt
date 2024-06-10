package com.example.apipokemon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface OnItemClickListener {
    fun onItemClick(pokemon: PokemonInfoResponse)
}

class RecyclerAdapter (private var pokemonList:  MutableList<PokemonInfoResponse> = mutableListOf()) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addPokemon(newPokemon: List<PokemonInfoResponse>) {
        pokemonList.addAll(newPokemon)
        notifyDataSetChanged()
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.ItemImage)
        val pokemonName: TextView = itemView.findViewById(R.id.ItemName)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(pokemonList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.pokemon_container, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: PokemonInfoResponse = pokemonList[position]

        val imageUrl = pokemon.sprites.image

        Glide.with(holder.pokemonImage.context)
            .load(imageUrl)
            .into(holder.pokemonImage)

        holder.pokemonName.text = pokemon.forms[0].value
    }

}