package com.jovian.fragmentexample02.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jovian.fragmentexample02.`interface`.OnFragmentActionsListener
import com.jovian.fragmentexample02.R
import com.jovian.fragmentexample02.fragments.BlueFragment
import com.jovian.fragmentexample02.fragments.RedFragment


class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    private val btnRed: Button by lazy { findViewById(R.id.btnRed) }
    private val btnBlue: Button by lazy { findViewById(R.id.btnBlue)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRed.setOnClickListener { loadFragment(RedFragment()) }
        btnBlue.setOnClickListener { loadFragment(BlueFragment()) }
    }

    override fun onClickFragmentButton() {

        Toast.makeText(this, "Button has been pushed", Toast.LENGTH_SHORT).show()

    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}