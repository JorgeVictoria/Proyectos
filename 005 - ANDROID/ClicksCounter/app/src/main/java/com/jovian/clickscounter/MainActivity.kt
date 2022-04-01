package com.jovian.clickscounter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.clickscounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //declaracion de variables
    var counter = 0 //creates and inits a counter variable
    private lateinit var binding: ActivityMainBinding //para almacener los elementos de la vista

    /**
    //esta variable se iniciará la primera vez que se use
    val tvClickCounter : TextView by lazy { findViewById(R.id.tvContador)}

    //otra variable que se inicia tardiamente
    lateinit var btnIncreaseCounter: Button*/

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cargamos el binding con los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        //mostramos el contenido
        setContentView(view)

        //le creamos el listener al Button
        binding.btnIncrementar.setOnClickListener() {
            counter++
            binding.tvContador.setText("You have clicked $counter times")
        }



        /*
        setContentView(R.layout.activity_main)

        //aqui inicializamos el boton que hemos declarado arriba
        btnIncreaseCounter = findViewById(R.id.btnIncrementar) as Button*/
    }
}