package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        val button: Button = findViewById<Button>(R.id.button6)
        val button2: Button = findViewById<Button>(R.id.button7)

        button.setOnClickListener() {
            Toast.makeText(this, "boto apretat", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener() {
            val i = Intent(this, MainActivity7::class.java)
            startActivity(i)
        }
    }
}