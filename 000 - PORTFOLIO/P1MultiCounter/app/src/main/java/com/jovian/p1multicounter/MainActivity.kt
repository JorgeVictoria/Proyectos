package com.jovian.p1multicounter

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jovian.p1multicounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //variables
    private lateinit var binding: ActivityMainBinding //nombre del layout
    var contFila1: Int = 0 //contador fila. Es el contador global. El de la fila 1
    var contFila2: Int = 0 //contador fila 2
    var contFila3: Int = 0 //contador fila 3
    var contFila4: Int = 0 //contador fila 4
    var contFila5: Int = 0 //contador fila 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //le pasamos todos los elementos del layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        //vista raiz
        val view = binding.root

        setContentView(view)

        /**
         * funcion que cambia los valores de los distintos contadores
         * @param cabecera: el contador de la cabecera
         * @param fila: el contador de una fila
         * @param valorTotal: es el valor del contador cabecera
         * @param valor: es el valor del contador de la fila que invoca
         */
        fun pintar(cabecera: TextView, fila : TextView, valorTotal: Int, valor: Int){
            //imprimimos por pantalla los dos valores, el global y el de la fila
            fila.text = "$valor"
            cabecera.text = "$valorTotal"

            //cambiamos el color segun el valor del contador
            //negro = 0, verde > 0, rojo < 0
            //primero la fila
            if(valor > 0) fila.setTextColor(Color.parseColor("#5db242"))
            else if(valor < 0) fila.setTextColor(Color.parseColor("#d92b20"))
            else fila.setTextColor(Color.parseColor("#FF000000"))
            //posteriormente la cabecera que tiene el contador global
            if(valorTotal > 0) cabecera.setTextColor(Color.parseColor("#5db242"))
            else if(valorTotal < 0) cabecera.setTextColor(Color.parseColor("#d92b20"))
            else cabecera.setTextColor(Color.parseColor("#FF000000"))

        }

        //distintas llamadas a eventos de boton
        //en funcion del boton que pulsemos, incrementamos o decrementamos
        //el contador global y el contador de la fila, valor que se almacenca en sus variables
        //respectivas. Posteriormente se llama a la funcion pintar, que cambiara los textos del
        //contador asi como su funcion
        binding.IncrementarFila2.setOnClickListener(){
            contFila1++
            contFila2++
            pintar(binding.ContadorGlobal, binding.contadorFila2, contFila1, contFila2)
        }

        binding.DecrementarFila2.setOnClickListener(){
            contFila1--
            contFila2--;
            pintar(binding.ContadorGlobal, binding.contadorFila2, contFila1, contFila2)

        }

        binding.resetFila2.setOnClickListener() {
            contFila1 = contFila1 - contFila2
            contFila2=0;
            pintar(binding.ContadorGlobal, binding.contadorFila2, contFila1, contFila2)
        }

        binding.IncrementarFila3.setOnClickListener(){
            contFila1++
            contFila3++
            pintar(binding.ContadorGlobal, binding.contadorFila3, contFila1, contFila3)
        }

        binding.DecrementarFila3.setOnClickListener(){
            contFila1--
            contFila3--;
            pintar(binding.ContadorGlobal, binding.contadorFila3, contFila1, contFila3)

        }

        binding.resetFila3.setOnClickListener() {
            contFila1 = contFila1 - contFila3
            contFila3=0;
            pintar(binding.ContadorGlobal, binding.contadorFila3, contFila1, contFila3)
        }

        binding.IncrementarFila4.setOnClickListener(){
            contFila1++
            contFila4++
            pintar(binding.ContadorGlobal, binding.contadorFila4, contFila1, contFila4)
        }

        binding.DecrementarFila4.setOnClickListener(){
            contFila1--
            contFila4--;
            pintar(binding.ContadorGlobal, binding.contadorFila4, contFila1, contFila4)

        }

        binding.resetFila4.setOnClickListener() {
            contFila1 = contFila1 - contFila4
            contFila4=0;
            pintar(binding.ContadorGlobal, binding.contadorFila4, contFila1, contFila4)
        }

        binding.IncrementarFila5.setOnClickListener(){
            contFila1++
            contFila5++
            pintar(binding.ContadorGlobal, binding.contadorFila5, contFila1, contFila5)
        }

        binding.DecrementarFila5.setOnClickListener(){
            contFila1--
            contFila5--;
            pintar(binding.ContadorGlobal, binding.contadorFila5, contFila1, contFila5)

        }

        binding.resetFila5.setOnClickListener() {
            contFila1 = contFila1 - contFila5
            contFila5=0;
            pintar(binding.ContadorGlobal, binding.contadorFila5, contFila1, contFila5)
        }

        //este evento es invocado desde la fila del contador global. Si se pulsa, todos los
        //contadores se ponen a 0, con color negro.
        binding.ReseteoGlobal.setOnClickListener() {
            //ponemos todos los contadores a 0
            contFila1 = 0
            contFila2 = 0
            contFila3 = 0
            contFila4 = 0
            contFila5 = 0

            //escribimos en pantalla
            binding.ContadorGlobal.text = "$contFila1"
            binding.contadorFila2.text = "$contFila2"
            binding.contadorFila3.text = "$contFila3"
            binding.contadorFila4.text = "$contFila4"
            binding.contadorFila5.text = "$contFila5"

            //ponemos el texto en color negro
            binding.ContadorGlobal.setTextColor(Color.parseColor("#FF000000"))
            binding.contadorFila2.setTextColor(Color.parseColor("#FF000000"))
            binding.contadorFila3.setTextColor(Color.parseColor("#FF000000"))
            binding.contadorFila4.setTextColor(Color.parseColor("#FF000000"))
            binding.contadorFila5.setTextColor(Color.parseColor("#FF000000"))

        }




    }


}