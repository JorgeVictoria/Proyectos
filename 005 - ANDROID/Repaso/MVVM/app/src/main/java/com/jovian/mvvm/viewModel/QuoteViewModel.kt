package com.jovian.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jovian.mvvm.model.QuoteModel
import com.jovian.mvvm.model.QuoteProvider

class QuoteViewModel: ViewModel() {

    //nos suscribimos a un modelo de datos y cogemos los cambios
    //encapsulamos el modelo en un livedata
    val quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote(){

        //llamamos al modelo para coger una quote
        val currentQuote: QuoteModel = QuoteProvider.random()

        //asignamos al live data el valor del quote
        quoteModel.postValue(currentQuote)
    }


}