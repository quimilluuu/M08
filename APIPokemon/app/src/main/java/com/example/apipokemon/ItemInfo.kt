package com.example.apipokemon

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class ItemInfo {

    @Serializable
    data class ItemImage (
        @SerializedName("default") val image : String?
    )
}