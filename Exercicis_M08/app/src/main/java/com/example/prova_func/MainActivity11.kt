package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        val button: Button = findViewById<Button>(R.id.button15)

        button.setOnClickListener() {
            val i = Intent(this, MainActivity16::class.java)
            startActivity(i)
        }
    }
}