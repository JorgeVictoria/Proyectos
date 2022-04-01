package com.jovian.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovian.mvvm.data.QuoteProvider
import com.jovian.mvvm.domain.GetQuotesUseCase
import com.jovian.mvvm.domain.GetRandomQuotesCase
import com.jovian.mvvm.model.QuoteModel
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuotesCase = GetRandomQuotesCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: List<QuoteModel>? = getQuotesUseCase()

            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    //a esta funcion se le llama desde el activity
    fun randomQuote(){

        isLoading.postValue(true)
        val quote: QuoteModel? = getRandomQuotesCase()
        if (quote!=null){
            quoteModel.postValue(quote!!)
        }
        isLoading.postValue(false)
        //desde aqui llamamos a la funcion del provider y nos dara una cita random
        //val currentQuote: QuoteModel = QuoteProvider.random()

        //asignamos el valor al livedata
        //quoteModel.postValue(currentQuote)
    }


}