package com.jovian.chat01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.chat01.databinding.ActivityChat01Binding
import model.Mensaje
import java.text.SimpleDateFormat
import java.util.*

class chat01 : AppCompatActivity() {

    private lateinit var binding: ActivityChat01Binding
    private var cadena: String = ""
    private var date : Date? = null
    private var dateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yy HH:mm")
    private var fecha: String = ""
    private var miConversacion: MutableList<Mensaje> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //almacenamos todos los elementos del layout y los mostramos en pantalla
        binding = ActivityChat01Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSend.setOnClickListener() {

            mostrarTexto()
        }
    }

    private fun mostrarTexto() {

        //cogemos el momento en el que
        date = Calendar.getInstance().time
        val fecha = dateFormat.format(date)
        cadena = ""

        val mensaje: Mensaje = Mensaje("s", "$fecha", binding.etTexto.text.toString())
        miConversacion.add(mensaje)

        /**
        //si es la primera lectura del String, cogemos el texto
        if(cadena.length == 0) cadena = fecha + "\n" + binding.etTexto.text.toString()

        //si cadena ya tiene contenido, le añadimos el texto
        else cadena = cadena + "\n" + fecha + "\n" + binding.etTexto.text.toString()

        binding.tvChat.text = cadena**/
        for(unMensaje in miConversacion){
            var elMensaje: Mensaje = unMensaje
            cadena = cadena + elMensaje.fecha + "\n" + elMensaje.mensaje.toString()
        }

        binding.tvChat.text = cadena


    }
}