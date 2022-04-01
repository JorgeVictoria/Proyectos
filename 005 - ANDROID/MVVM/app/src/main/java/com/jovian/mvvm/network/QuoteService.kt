package com.jovian.mvvm.network

import com.jovian.mvvm.core.RetrofitHelper
import com.jovian.mvvm.model.QuoteModel
import com.jovian.mvvm.viewModel.QuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO) {
            val response: Response<List<QuoteModel>> = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            return@withContext response.body() ?: emptyList()
        }

    }
}