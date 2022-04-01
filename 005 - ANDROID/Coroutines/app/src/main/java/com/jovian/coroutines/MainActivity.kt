package com.jovian.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //GFG()
        /**GlobalScope.launch {
            GFGASYNC()
        }**/

        GlobalScope.launch {
            testWithContext()
        }

        ///***********suspend fun*****************************
        //coroutine para llamar a una suspend fun
        /**GlobalScope.launch {
            Log.d("Main-Activity", "Call to Network")
            val networkCallAnswer=doNetworkCall()
            Log.d("Main-Activity", networkCallAnswer)
        }**/
    }


    /**suspend fun doNetworkCall(): String {
        delay(5000L)
        return "Network Call Answer"
    }**/

    //*************Launch*******************************
    //Launch para cosas que no afecten al usuario
    //finaliza la funcion antes que los hilos
    /**fun GFG()
    {
        var resultOne = "Android"
        var resultTwo = "Kotlin"
        Log.i("Launch", "Before")
        GlobalScope.launch(Dispatchers.IO) { resultOne = function1()}
        GlobalScope.launch(Dispatchers.IO) { resultTwo = function2() }
        Log.i("Launch", "After")
        val resultext = resultOne + resultTwo
        Log.i("Launch", resultext)
    }

    suspend fun function1(): String {
        delay(10000L)
        val message = "function1"
        Log.i("Launch", message)
        return message
    }

    suspend fun function2(): String {
        delay(5000L)
        val message = "function2"
        Log.i("Launch", message)
        return message
    }**/

    //**********************async************************
    //ASYNC, PARA RESULTADOS DE MULTIPLES TAREAS QUE SE EJECUTAN EN PARALELO
    // kotlin program for demonstration of async
    //la funcion no finaliza hasta que finalicen los hilos
    //primero finaliza el hilo2 y luego el hilo1, por el tiempo de retardo
    /**suspend fun GFGASYNC()
    {
        Log.i("Async", "Before")
        val resultOne = GlobalScope.async(Dispatchers.IO) { function1() }
        val resultTwo = GlobalScope.async (Dispatchers.IO) { function2() }
        Log.i("Async", "After")
        val resultText = resultOne.await() + resultTwo.await()
        Log.i("Async", resultText)
    }

    suspend fun function1(): String
    {
        delay(5000L)
        val message = "function1"
        Log.i("Async", message)
        return message
    }

    suspend fun function2(): String
    {
        delay(1000L)
        val message = "function2"
        Log.i("Async", message)
        return message
    }**/

    //*****************WITH CONTEXT**************************
    //aunque el segundo hilo es mas corto que el primero, primero finaliza el hilo1 y luego el hilo2
    //el metoda acaba tras acabar los hilos
    /**suspend fun testWithContext() {
        var resultOne = "Android"
        var resultTwo = "Kotlin"
        Log.i("withContext", "Before")
        resultOne = withContext(Dispatchers.IO) { function1() }
        resultTwo = withContext(Dispatchers.IO) { function2() }
        Log.i("withContext", "After")
        val resultText = resultOne + resultTwo
        Log.i("withContext", resultText)
    }

    suspend fun function1(): String {
        delay(10000L)
        val message = "function1"
        Log.i("withContext", message)
        return message
    }

    suspend fun function2(): String {
        delay(5000L)
        val message = "function2"
        Log.i("withContext", message)
        return message
    }**/

    //********************CONTROL DE JOB**************



}