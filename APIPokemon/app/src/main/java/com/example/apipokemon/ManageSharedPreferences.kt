package com.example.apipokemon

import android.content.Context

object ManageSharedPreferences {
    private const val Preferences = "favourites"
    private const val Key = "favourite_pokemons"

    fun getFavourites(context: Context): Set<String> {
        val prefs = context.getSharedPreferences(Preferences, Context.MODE_PRIVATE)
        val favouritesSet = prefs.getStringSet(Key, setOf()) ?: setOf()
        return favouritesSet.map { it.toString() }.toSet()
    }

    fun addFavourite(context: Context, pokemon: String) {
        val prefs = context.getSharedPreferences(Preferences, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val favouritesSet = getFavourites(context).toMutableSet()
        favouritesSet.add(pokemon)
        editor.putStringSet(Key, favouritesSet.map { it.toString() }.toSet())
        editor.apply()
    }

    fun removeFavourite(context: Context, pokemon: String) {
        val prefs = context.getSharedPreferences(Preferences, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val favouritesSet = getFavourites(context).toMutableSet()
        favouritesSet.remove(pokemon)
        editor.putStringSet(Key, favouritesSet.map { it.toString() }.toSet())
        editor.apply()
    }

    fun isFavourite(context: Context, pokemon: String): Boolean {
        val favouritesSet = getFavourites(context)
        return favouritesSet.contains(pokemon)
    }
}