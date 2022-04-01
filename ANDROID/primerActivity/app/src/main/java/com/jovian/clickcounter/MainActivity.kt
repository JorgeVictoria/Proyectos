package com.jovian.clickcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.jovian.clickcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //variables globales
    private var contador: Int = 0
    private lateinit var binding: ActivityMainBinding


    //metodo que se ejecuta primero cuando se inicia la app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //almacenamos todos los elementos del layout y los mostramos en pantalla
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //le ponemos un listenes al boton para que llame a una funcion al hacer click
        binding.btnClick.setOnClickListener(){
            incremenentarContador()
        }


    }

    /**
     * metodo para incrementar el contador de clicks
     */
    private fun incremenentarContador() {

        //incrementamos el contador
        contador++;
        //lo mostramos por pantalla
        binding.tvClickCounter.text = "$contador clicks"

    }
}