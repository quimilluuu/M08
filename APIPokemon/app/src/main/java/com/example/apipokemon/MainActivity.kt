package com.example.apipokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Response

private lateinit var adapter: RecyclerAdapter
private var page = 0
private var loadingMovies = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val returnButton: Button = findViewById(R.id.Items)

        returnButton.setOnClickListener() {
            val itemIntent = Intent(this@MainActivity, ItemActivity::class.java)
            startActivity(itemIntent)
        }

        val favouriteButton: Button = findViewById(R.id.FavouriteButton)

        favouriteButton.setOnClickListener() {
            val itemIntent = Intent(this@MainActivity, FavouritesActivity::class.java)
            startActivity(itemIntent)
        }

        RecyclerViewSetUp()
        LoadMovies()

        val recyclerView: RecyclerView = findViewById(R.id.PokemonContainer)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val childCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!loadingMovies && childCount + firstVisibleItemPosition >= itemCount && firstVisibleItemPosition >= 0) {
                    loadingMovies = true
                    LoadMovies()
                }
            }
        })

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(pokemon: PokemonInfoResponse) {
                val intent = Intent(this@MainActivity, PokemonStats::class.java).apply {
                    putExtra("name", pokemon.forms[0].value)
                    putExtra("hp", pokemon.stats[0].value)
                    putExtra("attack", pokemon.stats[1].value)
                    putExtra("defense", pokemon.stats[2].value)
                    putExtra("spAttack", pokemon.stats[3].value)
                    putExtra("spDefense", pokemon.stats[4].value)
                    putExtra("speed", pokemon.stats[5].value)
                    putExtra("image", pokemon.sprites.image)
                }
                startActivity(intent)
            }
        })
    }



    private fun RecyclerViewSetUp() {
        val recyclerView: RecyclerView = findViewById(R.id.PokemonContainer)
        adapter = RecyclerAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun LoadMovies() {
        val offset = page * amountPerCall
        lifecycleScope.launch {
            try {
                val listResponse: Response<PokemonUrlResponse> = PokemonList.retrofitService.getPokemonList(offset, amountPerCall)

                if (listResponse.body() != null) {
                    val pokemonUrlList = listResponse.body()!!.results
                    val pokemonList:  MutableList<PokemonInfoResponse> = mutableListOf()

                    for (pokemon: PokemonURL.PokemonUrl in pokemonUrlList) {
                        if (pokemon.name != null) {
                            Log.d("pokemon", pokemon.name)
                            val pokemonResponse: Response<PokemonInfoResponse> =
                                Pokemon.retrofitService.getPokemon(pokemon.name)

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
                    page++
                    Log.d("pokemon", pokemonList.size.toString())
                }
            }
            catch (e: Exception) {
                Log.e("MainActivity", "Error loading pokemon: ${e.message}")
            }
        }
        loadingMovies = false
    }
}