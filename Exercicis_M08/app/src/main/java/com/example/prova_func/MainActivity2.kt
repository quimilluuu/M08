package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button: Button = findViewById<Button>(R.id.button2)

        button.setOnClickListener() {
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
        }
    }
}