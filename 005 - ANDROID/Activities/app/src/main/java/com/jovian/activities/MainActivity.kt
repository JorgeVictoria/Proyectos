package com.jovian.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //para almacener los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cargamos el binding con los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        //ponemos el listener al boton y volvemos a la MainActivity
        binding.btnGo.setOnClickListener() {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
    }

}