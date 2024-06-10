package com.example.apipokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Response

class FavouritesActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        val returnButton: Button = findViewById(R.id.ReturnButton)

        returnButton.setOnClickListener {
            finish()
        }

        RecyclerViewSetUp()
        LoadMovies()
    }

    private fun RecyclerViewSetUp() {
        val recyclerView: RecyclerView = findViewById(R.id.FavouriteContainer)
        adapter = RecyclerAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun LoadMovies() {
        lifecycleScope.launch {
            try {
                val pokemonList:  MutableList<PokemonInfoResponse> = mutableListOf()

                for (pokemon: String in ManageSharedPreferences.getFavourites(applicationContext)) {
                    if (pokemon != null) {
                        Log.d("pokemon", pokemon)
                        val pokemonResponse: Response<PokemonInfoResponse> =
                            Pokemon.retrofitService.getPokemon(pokemon)

                        if (pokemonResponse.isSuccessful) {
                            val newPokemon: PokemonInfoResponse = pokemonResponse.body()!!
                            Log.d("pokemon", newPokemon.toString())
                            pokemonList.add(newPokemon)
                        }
                        else {
                            Log.e("pokemon", "Error loading pokemon")
                        }
                    }
                    else {
                        Log.e("pokemon", "Error loading pokemon")
                    }
                }

                adapter.addPokemon(pokemonList)
                Log.d("pokemon", pokemonList.size.toString())
            }
            catch (e: Exception) {
                Log.e("MainActivity", "Error loading pokemon: ${e.message}")
            }
        }
    }
}