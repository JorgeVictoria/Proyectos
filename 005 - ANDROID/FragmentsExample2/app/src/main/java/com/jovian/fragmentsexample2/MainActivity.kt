package com.jovian.fragmentsexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(RedFragment())

        var blueButton = findViewById(R.id.btnBlue) as Button
        var redButton = findViewById(R.id.btnRed) as Button

        redButton.setOnClickListener { replaceFragment(RedFragment()) }
        blueButton.setOnClickListener { replaceFragment(BlueFragment()) }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton() {
        Toast.makeText(this, "Button has been pushed", Toast.LENGTH_SHORT).show()
    }
}