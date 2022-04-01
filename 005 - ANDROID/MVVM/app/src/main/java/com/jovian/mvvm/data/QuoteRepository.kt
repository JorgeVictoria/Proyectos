package com.jovian.mvvm.data

import com.jovian.mvvm.model.QuoteModel
import com.jovian.mvvm.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response: List<QuoteModel> = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}