package com.example.apipokemon

import com.google.gson.Gson
import kotlinx.serialization.SerialName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"
const val amountPerCall = 20

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val gsonInstance = Gson()

interface PokemonListApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonUrlResponse>
}

interface PokemonInfoApi {
    @GET("pokemon/{pokemon}")
    suspend fun getPokemon(
        @Path("pokemon") pokemonName : String
    ): Response<PokemonInfoResponse>
}

interface ItemListApi {
    @GET("item")
    suspend fun getItemList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ItemUrlResponse>
}

interface ItemInfoApi {
    @GET("item/{item}")
    suspend fun getItems(
        @Path("item") pokemonName : String
    ): Response<ItemResponse>
}

data class PokemonUrlResponse(
    val results: List<PokemonURL.PokemonUrl>,
    val offset: Int
)

data class PokemonInfoResponse(
    val sprites: PokemonInfo.PokemonImage,
    val stats: List<PokemonInfo.Stat>,
    val forms: List<PokemonInfo.Name>
)

data class ItemUrlResponse(
    val results: List<ItemURL.ItemUrl>,
    val offset: Int
)

data class ItemResponse(
    val name: String,
    val sprites: ItemInfo.ItemImage
)

object PokemonList {
    val retrofitService: PokemonListApi by lazy {
        retrofit.create(PokemonListApi::class.java)
    }
}

object Pokemon {
    val retrofitService: PokemonInfoApi by lazy {
        retrofit.create(PokemonInfoApi::class.java)
    }
}

object ItemList {
    val retrofitService: ItemListApi by lazy {
        retrofit.create(ItemListApi::class.java)
    }
}

object Item {
    val retrofitService: ItemInfoApi by lazy {
        retrofit.create(ItemInfoApi::class.java)
    }
}





