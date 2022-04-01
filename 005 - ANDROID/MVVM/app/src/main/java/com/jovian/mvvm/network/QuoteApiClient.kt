package com.jovian.mvvm.network

import com.jovian.mvvm.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>

}