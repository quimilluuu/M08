package com.example.prova_func

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        val button: Button = findViewById<Button>(R.id.button9)
        val editText: EditText = findViewById<EditText>(R.id.editTextText2)
        val textViewer: TextView = findViewById<TextView>(R.id.textView5)
        val buttonNext: Button = findViewById<Button>(R.id.button10)

        button.setOnClickListener() {
            textViewer.text = editText.editableText
        }

        buttonNext.setOnClickListener() {
            val i = Intent(this, MainActivity9::class.java)
            startActivity(i)
        }
    }
}