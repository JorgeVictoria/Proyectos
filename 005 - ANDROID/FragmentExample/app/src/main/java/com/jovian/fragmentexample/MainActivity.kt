package com.jovian.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickFragmentButton() {
        Toast.makeText(this, "Button has been pushed", Toast.LENGTH_SHORT).show()
    }
}