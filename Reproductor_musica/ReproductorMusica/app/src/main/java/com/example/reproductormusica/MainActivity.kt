package com.example.reproductormusica

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stopped = false;

        var mediaPlayer = MediaPlayer.create(this, R.raw.music1)
        var playButton : Button = findViewById<Button>(R.id.play_button)
        var pauseButton : Button = findViewById<Button>(R.id.pause_button)
        var stopButton : Button = findViewById<Button>(R.id.stop_button)

        var length = 0;

        playButton.setOnClickListener {
            mediaPlayer.start()
            stopped = false
        }

        pauseButton.setOnClickListener {
            Log.d("debug","pause")
            if (!stopped) {
                mediaPlayer.pause()
                length = mediaPlayer.getCurrentPosition();
                stopped = true
            }
            else {
                mediaPlayer.seekTo(length);
                mediaPlayer.start();
                stopped = false
            }
        }

        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepareAsync()
        }

    }
}