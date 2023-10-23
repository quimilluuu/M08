package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val button: Button = findViewById<Button>(R.id.button3)
        val button2: Button = findViewById<Button>(R.id.button5)

        button.setOnClickListener() {
            Log.d("prova", "boto apretat")
        }

        button2.setOnClickListener() {
            val i = Intent(this, MainActivity6::class.java)
            startActivity(i)
        }
    }
}