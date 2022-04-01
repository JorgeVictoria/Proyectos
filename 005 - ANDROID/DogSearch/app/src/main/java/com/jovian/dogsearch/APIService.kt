package com.jovian.dogsearch

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Tipo de llamada: GET
 * @param: url. String con una ruta URL
 * @return: el response de DogsResponse
 */
interface APIService {

    @GET
    suspend fun getDogsByBreeds (@Url url: String): Response<DogsResponse>
}