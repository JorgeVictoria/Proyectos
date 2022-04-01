package com.jovian.p4livedata.model

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

//vamos a usar funciones, asi que nos creamos un typealias
typealias  onOrder = (order:String) -> Unit


class Partido {

    //variable para crear numeros aleatorios
    val random: Random = Random()

    //variable de tipo coroutine
    var match: Job? = null

    //variable para almacenar el evento del partido
    var eventoPartido = -1

    //variable para almacenar el tiempo del partido
    var tiempoPartido = 0

    //variables de marcador
    var marcadorLocal = 0
    var marcadorVisitante = 0

    //variables de cuotas
    var cuotaUno: Double = 3.00
    var cuotaEmpate: Double = 3.00
    var cuotaDos: Double = 3.00

    //livedata para enlazar con el viewModel
    val matchLiveData: LiveData<String> = object:LiveData<String>(){
        override fun onActive() {
            super.onActive()
            startMatch { order ->
                postValue(order)
            }
        }
    }


    //funcion para dar inicio y gestionar el partido

    fun startMatch(onOrder: onOrder){

        //si match es null, que ocurre inicialmente, se inicia el partido
        //si no hay un partido iniciado o el partido no ha sido cancelado, se inicia el partido
        //importante dejarlo asi, porque sino al finalizar el partido y girar el dispositivo, se inicia un nuevo partido
        if(match == null || (!match!!.isActive && !match!!.isCancelled)) {

            //lanzamos una nueva coroutine
            match = CoroutineScope(Dispatchers.IO).launch {

                //mientras el partido este en marcha
                while (true) {

                    /************EVENTOS DE INICIO, DESCANSO Y FIN DE PARTIDO**********************/
                    //desde aqui controlamos si es el inicio del partido, el descanso o el final del partido
                    if (tiempoPartido == 0) eventoPartido = 0
                    else if (tiempoPartido == 45) eventoPartido = 101
                    else if (tiempoPartido == 90) eventoPartido = 102


                    /*********EVENTOS DEL PARTIDO**************/
                    //si el tiempo de partido es menor de 90, creamos eventos de partido
                    else if ((tiempoPartido >= 1 && tiempoPartido <= 44) || (tiempoPartido >= 46 && tiempoPartido <= 99)){
                        /**creamos eventos de partido, de manera que:
                         * un valor de 1 a 3, gol local
                         * un valor de 3 a 20, ataque local
                         * un valor de 21 a 50 posesion local
                         * un valor de 51 a 80 posesion visitante
                         * un valor de 81 a 97 ataque visitante
                         * un valor de 97 a 100 gol visitante
                         */
                        eventoPartido = random.nextInt(99) + 1
                    }

                    /************GOLES Y CUOTAS***************/
                    //Si los eventos conllevan gol, aumentamos el marcador correspondiente y las cuotas varian
                    //hay gol local
                    if (eventoPartido >= 1 && eventoPartido <= 3) {
                        marcadorLocal++
                        cuotaUno = cuotaUno - 0.50
                        cuotaDos = cuotaDos + 0.50
                    }
                    //hay gol visitante
                    if (eventoPartido >= 98 && eventoPartido <= 100) {
                        marcadorVisitante++
                        cuotaUno = cuotaUno + 0.50
                        cuotaDos = cuotaDos - 0.50
                    }

                    //control de cuota de cuota de empate
                    //el calculo de la cuota es un poco arreu, porque no se muy bien como establecer la relacion de su calculo
                    //TODO estudiar un partido del bet365 con cuotas similares y ver comportamiento
                    if(cuotaUno == cuotaDos) cuotaEmpate = 3.00
                    else if(cuotaUno > cuotaDos) cuotaEmpate = cuotaUno - 0.25
                    else cuotaEmpate = cuotaDos - 0.25

                    //control de cuotas inferiores a uno
                    if(cuotaUno < 1) cuotaUno = 1.00
                    if(cuotaDos < 2) cuotaDos = 1.00

                    /*****************************LO QUE ENVIAMOS AL VIEW MODEL*********************/

                    //creamos la cadena que vamos a enviar al viewModel con el evento y el tiempo de partido
                    if (eventoPartido > 0 && eventoPartido <= 3) onOrder("GOLLOCAL:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido > 3 && eventoPartido <= 20) onOrder("PELIGROLOCAL:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido > 20 && eventoPartido <= 50) onOrder("POSESIONLOCAL:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido > 50 && eventoPartido <= 80) onOrder("POSESIONVISITANTE:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido > 80 && eventoPartido <= 97) onOrder("PELIGROVISITANTE:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido > 97 && eventoPartido <= 100) onOrder("GOLVISITANTE:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido == 101) onOrder("DESCANSO:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else if (eventoPartido == 102) onOrder("FINALPARTIDO:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())
                    else onOrder("INICIOPARTIDO:" + tiempoPartido.toString() + ":" + marcadorLocal.toString() + ":" + marcadorVisitante.toString() + ":" + cuotaUno.toString() + ":" + cuotaEmpate.toString() + ":" + cuotaDos.toString())

                    /******************************OTROS DETALLES**************************/
                    //incrementamos en uno el tiempo de partido
                    tiempoPartido++

                    //aplicamos un delay de 1 segundo, periodo que corresponde a cada minuto del partido
                    //y donde ocurrira un evento diferente
                    delay(1000)

                    //si llegamos al final de partido , paramos el job
                    if (tiempoPartido == 90){
                        match?.cancel("fin del partido")
                    }

                }
            }
        }
    }
}