package com.jovian.repte.interfaces

import com.jovian.repte.model.PedidoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPedidoById(@Url url: String): Response<PedidoResponse>
    abstract fun getPedidoById(): Response<PedidoResponse>
}