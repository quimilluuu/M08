package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        val button: Button = findViewById<Button>(R.id.button8)

        button.setOnClickListener() {
            val i = Intent(this, MainActivity8::class.java)
            startActivity(i)
        }
    }
}