package com.example.apipokemon

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class PokemonURL {

    @Serializable
    data class PokemonUrl (
        @SerializedName("name") val name : String?
    )
}