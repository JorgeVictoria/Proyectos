package com.jovian.test1ev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Test1ev)
        Thread.sleep(2000)

        setContentView(R.layout.activity_main)
    }
}