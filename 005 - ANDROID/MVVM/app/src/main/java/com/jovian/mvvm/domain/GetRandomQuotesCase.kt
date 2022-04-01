package com.jovian.mvvm.domain

import com.jovian.mvvm.data.QuoteProvider
import com.jovian.mvvm.data.QuoteRepository
import com.jovian.mvvm.model.QuoteModel

class GetRandomQuotesCase {

    private val repository = QuoteRepository()

    operator fun invoke():QuoteModel?{
        var quotes: List<QuoteModel> = QuoteProvider.quotes
        if(!quotes.isNullOrEmpty()){
            val randomNumber: Int = (0..quotes.size-1).random()
            return quotes[randomNumber]
        }
        return null
    }
}