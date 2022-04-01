package com.jovian.mvvm.domain

import com.jovian.mvvm.data.QuoteRepository
import com.jovian.mvvm.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>?{
        return repository.getAllQuotes()
    }
}