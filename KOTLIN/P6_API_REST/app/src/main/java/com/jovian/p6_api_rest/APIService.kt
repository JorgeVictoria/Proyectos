package com.jovian.p6_api_rest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    //@GET("amiibo/?character={character}")
    @GET
    suspend fun getAmiiboByName(@Url url: String): Response<AmiiboResponse>
}