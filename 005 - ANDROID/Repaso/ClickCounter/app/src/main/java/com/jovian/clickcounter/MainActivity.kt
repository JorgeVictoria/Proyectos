package com.jovian.clickcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var counter : Int = 0
    val tvClickListener: TextView by lazy {findViewById(R.id.tvClicks)}
    val btnIncreaseClicks: Button by lazy { findViewById(R.id.btnClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIncreaseClicks.setOnClickListener(){
            increaseCounter()
            updateCounter()
        }
    }

    private fun updateCounter() {

        tvClickListener.setText("You have clicked $counter times")
    }

    private fun increaseCounter() {

        counter++;

    }


}