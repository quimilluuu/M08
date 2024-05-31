package com.example.simon

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import kotlin.random.Random
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.view.View
import android.view.animation.Animation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startingSequenceLongitude = 3
        var currentSequenceLongitude = 2
        var sequence = MutableList(3) { Random.nextInt(0, 4) }
        var inputedSequence = MutableList<Int>(0) {0}
        var correctlyAnswered = true
        var turn = 0

        val regiceButton : Button = findViewById<Button>(R.id.regice)
        val regirockButton : Button = findViewById<Button>(R.id.regirock)
        val registeelButton : Button = findViewById<Button>(R.id.registeel)
        val regigigasButton : Button = findViewById<Button>(R.id.regigigas)
        val startButton : Button = findViewById<Button>(R.id.start)


        val soundpool = SoundPool.Builder()
        val soundPool = soundpool.build()
        val soundRegice = soundPool.load(this, R.raw.regice, 1)
        val soundRegirock = soundPool.load(this, R.raw.regirock, 1)
        val soundRegisteel = soundPool.load(this, R.raw.registeel, 1)
        val soundRegigigas = soundPool.load(this, R.raw.regigigas, 1)

         regirockButton.setOnClickListener {
             soundPool.play(soundRegirock, 1f, 1f, 0, 0, 1f)
             inputedSequence.add(0)
             turn++
             val scaleAnimation = ScaleAnimation(
                 1.0f, 1.3f,
                 1.0f, 1.3f,
                 Animation.RELATIVE_TO_SELF, 0.5f,
                 Animation.RELATIVE_TO_SELF, 0.5f
             )
             regirockButton.startAnimation(scaleAnimation)
             scaleAnimation.duration = 1000 // Duración de la animación
             scaleAnimation.repeatCount = 3 // Repetir la animación una vez (total de 2 veces)
             if (turn == currentSequenceLongitude) {
                 Log.d("random", sequence.toString() + ", " + inputedSequence.toString())
                 if (sequence == inputedSequence) {
                     correctlyAnswered = true
                 }
                 else {
                     correctlyAnswered = false
                     currentSequenceLongitude = 3
                 }
             }
         }
         regiceButton.setOnClickListener {
             soundPool.play(soundRegice, 1f, 1f, 0, 0, 1f)
             inputedSequence.add(1)
             turn++
             val scaleAnimation = ScaleAnimation(
                 1.0f, 1.3f,
                 1.0f, 1.3f,
                 Animation.RELATIVE_TO_SELF, 0.5f,
                 Animation.RELATIVE_TO_SELF, 0.5f
             )
             regiceButton.startAnimation(scaleAnimation)
             if (turn == currentSequenceLongitude) {
                 Log.d("random", sequence.toString() + ", " + inputedSequence.toString())
                 if (sequence == inputedSequence) {
                     correctlyAnswered = true
                 }
                 else {
                     correctlyAnswered = false
                     currentSequenceLongitude = 3
                 }
             }
         }
         registeelButton.setOnClickListener {
             soundPool.play(soundRegisteel, 1f, 1f, 0, 0, 1f)
             inputedSequence.add(2)
             turn++
             val scaleAnimation = ScaleAnimation(
                 1.0f, 1.3f,
                 1.0f, 1.3f,
                 Animation.RELATIVE_TO_SELF, 0.5f,
                 Animation.RELATIVE_TO_SELF, 0.5f
             )
             registeelButton.startAnimation(scaleAnimation)
             if (turn == currentSequenceLongitude) {
                 Log.d("random", sequence.toString() + ", " + inputedSequence.toString())
                 if (sequence == inputedSequence) {
                     correctlyAnswered = true
                 }
                 else {
                     correctlyAnswered = false
                     currentSequenceLongitude = 3
                 }
             }
         }
         regigigasButton.setOnClickListener {
             soundPool.play(soundRegigigas, 1f, 1f, 0, 0, 1f)
             inputedSequence.add(3)
             turn++
             val scaleAnimation = ScaleAnimation(
                 1.0f, 1.2f,
                 1.0f, 1.2f,
                 Animation.RELATIVE_TO_SELF, 0.5f,
                 Animation.RELATIVE_TO_SELF, 0.5f
             )
             regigigasButton.startAnimation(scaleAnimation)
             if (turn == currentSequenceLongitude) {
                 Log.d("random", sequence.toString() + ", " + inputedSequence.toString())
                 if (sequence == inputedSequence) {
                     correctlyAnswered = true
                 }
                 else {
                     correctlyAnswered = false
                     currentSequenceLongitude = 3
                 }
             }
         }

        startButton.setOnClickListener {
            if (correctlyAnswered) {
                sequence.add(Random.nextInt(0,4))
                currentSequenceLongitude++

            }
            else {
                sequence = MutableList(startingSequenceLongitude) { Random.nextInt(0, 4) }
                currentSequenceLongitude = startingSequenceLongitude
            }
            turn = 0
            correctlyAnswered = false
            inputedSequence.clear()
            var wait : Long = 0
            for (i in sequence) {
                Handler().postDelayed({
                    when (i) {
                        0 -> soundPool.play(soundRegirock, 1f, 1f, 0, 0, 1f)
                        1 -> soundPool.play(soundRegice, 1f, 1f, 0, 0, 1f)
                        2 -> soundPool.play(soundRegisteel, 1f, 1f, 0, 0, 1f)
                        3 -> soundPool.play(soundRegigigas, 1f, 1f, 0, 0, 1f)
                    }
                }, wait)
                wait += 2000
            }
        }
    }
}