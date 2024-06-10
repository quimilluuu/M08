package com.example.apipokemon

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class PokemonInfo {

    @Serializable
    data class PokemonImage (
        @SerializedName("front_default") val image : String?
    )

    @Serializable
    data class Stat (
        @SerializedName("base_stat") val value : String?
    )

    @Serializable
    data class Name (
        @SerializedName("name") val value : String?
    )
}