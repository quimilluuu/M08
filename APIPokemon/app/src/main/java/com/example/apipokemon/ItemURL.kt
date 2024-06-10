package com.example.apipokemon

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class ItemURL {

    @Serializable
    data class ItemUrl (
        @SerializedName("name") val name : String?
    )
}