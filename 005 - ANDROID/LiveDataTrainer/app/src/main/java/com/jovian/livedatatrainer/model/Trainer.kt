package com.jovian.livedatatrainer.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

//vamos a usar funciones, asi que nos creamos un typealias
typealias  onOrder = (order:String) -> Unit

class Trainer {

    //variable para crear numeros aleatorios
    val random: Random = Random()

    //variable de tipo coroutine
    var training:Job? = null

    //variable para almacenar el ejercicio a realizar
    var exercise = 0

    //variable para almacenar el numero de repeticiones a realizar
    var repetitions = -1

    val orderLiveData: LiveData<String> = object:LiveData<String>(){
        override fun onActive() {
            super.onActive()
            startTraining {
                    order -> postValue(order)
            }
        }

        override fun onInactive() {
            super.onInactive()
            stopTraining()
        }
    }


     //funcion para dar inicio al entrenamiento

    fun startTraining(onOrder: onOrder){

        //si no hay un entrenamiento en marcha
        if(training == null || training!!.isCancelled || training!!.isCompleted) {

            //lanzamos una nueva coroutine
            training = CoroutineScope(Dispatchers.IO).launch {

                //mientras el entrenamiento este en marcha
                while(true) {

                    //si el numero de repeticiones es menor que 0, asignamos repeticiones y entrenamiento
                    if (repetitions < 0) {
                        repetitions = random.nextInt(3) + 3
                        exercise = random.nextInt(5) + 1
                    }


                    //creamos el objeto onOrder con el ejercicio y el numero de repeticiones
                    onOrder("EXERCISE" + exercise + ":" + if (repetitions == 0) "CHANGE" else repetitions)
                    repetitions--

                    delay(1000)
                }
            }
        }
    }

    //funcion para parar el entrenamiento
    fun stopTraining() {
        training?.let {
            if(it.isActive)
                it.cancel()
        }
        exercise = 0
        repetitions = -1
    }

}



