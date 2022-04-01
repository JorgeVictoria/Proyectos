package com.jovian.parcelables.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.jovian.parcelables.R
import com.jovian.parcelables.model.Person

class MainActivity : AppCompatActivity() {
    private val btnGo : Button by lazy { findViewById(R.id.btnGo) }
    private val tvGreeting : TextView by lazy { findViewById(R.id.tvGreeting) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(intent.hasExtra("ObjetoParcelable")){

            val person = intent.getParcelableExtra<Person>("ObjetoParcelable")

            var texto : String = "${person?.name} ${person?.surname}"

            tvGreeting.text = "Hello " + texto

            }

        btnGo.setOnClickListener { openActivity2() }



    }

    private fun openActivity2() {

        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)


    }

}