package com.jovian.p4livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_P4LIveData)
        Thread.sleep(3000)

        setContentView(R.layout.activity_main)
    }
}