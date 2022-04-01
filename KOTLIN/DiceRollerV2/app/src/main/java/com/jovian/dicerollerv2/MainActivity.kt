package com.jovian.dicerollerv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    //var diceImage: ImageView? = null
    private lateinit var diceImage1: ImageView
    private lateinit var diceImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRoll: Button = findViewById(R.id.btnRoll)
        val btnClear: Button = findViewById(R.id.btnClear)
        diceImage1 = findViewById(R.id.ivDice1)
        diceImage2 = findViewById(R.id.ivDice2)

        btnRoll.setOnClickListener { rollDice() }
        btnClear.setOnClickListener { clearDice() }


    }

    private fun clearDice() {

        diceImage1.setImageResource(R.drawable.empty_dice)
        diceImage2.setImageResource(R.drawable.empty_dice)
    }

    private fun rollDice() {

        diceImage1.setImageResource(getDrawableResource())
        diceImage2.setImageResource(getDrawableResource())

    }

    private fun calculateNumber(): Int {

        return (1..6).random()

    }

    private fun getDrawableResource() : Int {

        val drawableResource = when (calculateNumber()){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        return drawableResource

    }
}