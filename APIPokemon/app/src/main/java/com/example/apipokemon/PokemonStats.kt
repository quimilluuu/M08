package com.example.apipokemon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class PokemonStats : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_stats)

        val name = intent.getStringExtra("name")
        val hp = intent.getStringExtra("hp")
        val attack = intent.getStringExtra("attack")
        val defense = intent.getStringExtra("defense")
        val spAttack = intent.getStringExtra("spAttack")
        val spDefense = intent.getStringExtra("spDefense")
        val speed = intent.getStringExtra("speed")
        val image = intent.getStringExtra("image")

        val imageView: ImageView = findViewById(R.id.imageView)
        val nameText: TextView = findViewById(R.id.textView)
        val hpText: TextView = findViewById(R.id.hp)
        val attackText: TextView = findViewById(R.id.attack)
        val defenseText: TextView = findViewById(R.id.defense)
        val spAttackText: TextView = findViewById(R.id.spAttack)
        val spDefenseText: TextView = findViewById(R.id.spDefense)
        val speedText: TextView = findViewById(R.id.speed)


        val returnButton: Button = findViewById(R.id.Return)
        val favouriteButton: Button = findViewById(R.id.Favorite)

        nameText.text = name
        hpText.text = hp
        attackText.text = attack
        defenseText.text = defense
        spAttackText.text = spAttack
        spDefenseText.text = spDefense
        speedText.text = speed

        Glide.with(imageView.context)
            .load(image)
            .into(imageView)

        returnButton.setOnClickListener {
            finish()
        }

        favouriteButton.setOnClickListener {
            ManageSharedPreferences.addFavourite(applicationContext, name.toString())
        }
    }
}