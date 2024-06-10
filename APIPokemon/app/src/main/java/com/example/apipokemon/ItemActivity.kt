package com.example.apipokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Response

class ItemActivity : AppCompatActivity() {
    private lateinit var adapter: ItemRecycler
    private var page = 0
    private var loadingItem = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val returnButton: Button = findViewById(R.id.PokemonButton)

        returnButton.setOnClickListener() {
            val itemIntent = Intent(this@ItemActivity, MainActivity::class.java)
            startActivity(itemIntent)
        }

        RecyclerViewSetUp()
        LoadItems()

        val recyclerView: RecyclerView = findViewById(R.id.PokemonContainer)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val childCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!loadingItem && childCount + firstVisibleItemPosition >= itemCount && firstVisibleItemPosition >= 0) {
                    loadingItem = true
                    LoadItems()
                }
            }
        })
    }

    private fun RecyclerViewSetUp() {
        val recyclerView: RecyclerView = findViewById(R.id.PokemonContainer)
        adapter = ItemRecycler()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun LoadItems() {
        val offset = page * amountPerCall
        lifecycleScope.launch {
            try {
                val listResponse: Response<ItemUrlResponse> = ItemList.retrofitService.getItemList(offset, amountPerCall)

                if (listResponse.body() != null) {
                    val itemUrlList = listResponse.body()!!.results
                    val itemList:  MutableList<ItemResponse> = mutableListOf()

                    for (item: ItemURL.ItemUrl in itemUrlList) {
                        if (item.name != null) {
                            Log.d("pokemon", item.name)
                            val itemResponse: Response<ItemResponse> =
                                Item.retrofitService.getItems(item.name)

                            if (itemResponse.isSuccessful) {
                                val newItem: ItemResponse = itemResponse.body()!!
                                Log.d("pokemon", newItem.toString())
                                itemList.add(newItem)
                            }
                            else {
                                Log.e("pokemon", "Error loading items")
                            }
                        }
                        else {
                            Log.e("pokemon", "Error loading items")
                        }
                    }

                    adapter.addItem(itemList)
                    page++
                    Log.d("pokemon", itemList.size.toString())
                }
            }
            catch (e: Exception) {
                Log.e("MainActivity", "Error loading pokemon: ${e.message}")
            }
        }
        loadingItem = false
    }
}