package com.jovian.viewmodel.model

import kotlin.math.pow

class BMICalculator {

    data class Request(
        val peso: Double,
        val altura: Double
    )

    private fun calcBMI(peso: Double, altura: Double):Double = peso/(altura/100).pow(2)

    fun calculate(request: Request):Double{

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        return calcBMI(request.peso, request.altura)
    }
}