package com.jovian.viewmodel.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jovian.viewmodel.model.BMICalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMIViewModel : ViewModel(){

    private val bmiCalculator: BMICalculator = BMICalculator()

    val bmi : MutableLiveData<Double> = MutableLiveData()

     fun calculate(peso: Double, altura: Double) {

        CoroutineScope(Dispatchers.IO).launch {
            val bmiResult = bmiCalculator.calculate(BMICalculator.Request(peso, altura))
            bmi.postValue(bmiResult)
        }
    }


}