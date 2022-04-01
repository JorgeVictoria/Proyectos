package com.jovian.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.activities.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding //para almacener los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cargamos el binding con los elementos de la vista
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        //ponemos el listener al boton y volvemos a la MainActivity
        binding.btnReturn.setOnClickListener() {
            //si queremos sacar la activity de la pila
            finish()
            //se mantiene la activity en la pila
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
        }
    }
}