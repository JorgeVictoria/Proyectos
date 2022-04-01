package com.jovian.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRoll: Button = findViewById(R.id.btnRoll)
        val btnCountUp: Button = findViewById(R.id.btnAdd)
        val btnReset: Button = findViewById(R.id.btnReset)

        btnRoll.setOnClickListener { rollDice() }
        btnCountUp.setOnClickListener { addOne() }
        btnReset.setOnClickListener { resetDice() }

    }

    private fun resetDice() {

        val tvMessage: TextView = findViewById(R.id.tvMessage)

        tvMessage.text = "0"


    }

    private fun addOne() {

        val tvMessage: TextView = findViewById(R.id.tvMessage)
        val diceValue: String = tvMessage.text.toString()

        if(diceValue == "Click the button to Roll dice") tvMessage.text = "1"
        else if (diceValue != "6") {
            var number: Int = Integer.valueOf(tvMessage.text.toString())
            number++
            tvMessage.text = number.toString()
        }

    }

    private fun rollDice() {

        val tvMessage: TextView = findViewById(R.id.tvMessage)

        tvMessage.text = calculateNumber().toString()
        //tvMessage.text = "Dice Rolled!"
        //Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

    }

    private fun calculateNumber(): Int {

        return (1..6).random()

    }
}