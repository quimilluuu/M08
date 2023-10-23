package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        val button: Button = findViewById<Button>(R.id.button11)

        button.setOnClickListener() {
            val i = Intent(this, MainActivity10::class.java)
            startActivity(i)
        }
    }
}