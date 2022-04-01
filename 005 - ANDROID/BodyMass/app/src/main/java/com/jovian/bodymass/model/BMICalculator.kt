package com.jovian.bodymass.model

import java.lang.Exception
import kotlin.math.pow

typealias OnWrongHeight = (error:String)->Unit
typealias OnWrongWeight = (error:String)->Unit
typealias OnError = (error:String)->Unit
typealias OnSuccess = (bmi:Double)->Unit
typealias OnLoading = (loading:Boolean)->Unit

class BMICalculator {

    data class Request(
        val weight:Double,
        val height:Double
    )

    private fun calcMBI(weight: Double, height: Double):Double = weight/ (height/100).pow(2)

    /**********************************WITH FUNCTIONS****************************************/

    //Long calculating function
    fun calculateWithFunctions(request: Request,onSuccess: OnSuccess,onError: OnError,onLoading: OnLoading,onWrongWeight: OnWrongWeight?,onWrongHeight: OnWrongHeight?){

        //llamada a la funcion, pasando el valor true, ya que estamos realizando el calculo
        onLoading(true)

        //declaramos las variables con un minimo de peso y altura
        val minHeight = 50
        val minWeight = 10

        //partimos de que los datos van a ser correctos
        var error = false

        //le añadimos un retraso para simular un calculo largo
        Thread.sleep(5000)

        //imprimimos por consola el nombre del hilo
        println(Thread.currentThread().name)

        //si la altura es menor que la altura minima, llamamos al callback
        if(minHeight > request.height){
            onWrongHeight?.let {
                it.invoke("La altura debe ser mayor")
                error = true
            }
        }

        //si el peso es menor que el peso minimo, llamamos al callback
        if(minWeight > request.weight){
            onWrongWeight?.let {
                it.invoke("La altura debe ser mayor")
                error = true
            }
        }

        //si los datos introducidos son correctos, no hay error e inciamos el calculo
        if(!error){
            try{
                val bmi = calcMBI(request.weight, request.height)
                onSuccess(bmi)
            }catch (e:Exception){
                onError(e.toString())
            }
        }

        onLoading(false)

    }

    /************************************WITH SEALED CLASS*****************************************/

    sealed class Response{
        class OKResult(val result:Double):Response()
        class WrongWeight(val error:String):Response()
        class WrongHeight(val error:String):Response()
    }

    //Long calculating function with sealed class
    fun calculateWithSealed(request: Request,onLoading: OnLoading?):Response{
        onLoading?.invoke(true)

        //ponemos los minimos requeridos de altura y peso
        val minHeight = 50
        val minWeight = 10

        //comprobamos que los datos introducidos sean mayor que los requisitos minimos
        if(minWeight > request.weight) return (Response.WrongWeight("Weight should be bigger"))
        if(minWeight > request.weight) return (Response.WrongWeight("Weight should be bigger"))

        //ponemos una pausa para simular el calculo
        Thread.sleep(5000)

        //mostramos por consola el nombre del proceso o hilo
        println("${Thread.currentThread().name}")

        //si la app funciona bien

        //quitamos el circulito
        onLoading?.invoke(false)

        //devolvemos el calculo
        return Response.OKResult(calcMBI(request.weight, request.height))
    }

    /**********************************WITH CALLBACKS***********************************/

    interface BMIResponse{
        fun onSuccess(result:Double)
        fun onHeightError(error:String)
        fun onWeightError(error:String)
        fun onError(error:String)
        fun onLoading(loading: Boolean)
    }

    fun calculateWithCallback(request: Request, bmiResponse: BMIResponse){
        bmiResponse.onLoading(true)
        val minHeight= 50
        val minWeight = 10

        var error = false

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        //If height is lower than minHeight call ourCallback
        if(minHeight > request.height){
            bmiResponse.onHeightError("Height should be bigger")
            error = true
        }
        if(minWeight > request.weight){
            bmiResponse.onWeightError("Weight should be bigger")
            error = true
        }

        if(!error){
            //All works fine
            try{
                val bmi = calcMBI(request.weight, request.height)
                bmiResponse.onSuccess(bmi)
            }catch (e:Exception){
                bmiResponse.onError(e.toString())
            }
        }
        bmiResponse.onLoading(false)
    }
}