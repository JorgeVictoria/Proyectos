package com.jovian.p6_api_rest.interfaces

import com.jovian.p6_api_rest.model.AmiiboResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//metodo por el cual accedemos a nuestra API
//La api que consumo solo admite llamadas GET
interface APIService {
    //llamada
    @GET
    /**
     * funcion para buscar un personaje por su nombre
     * @param url, recibe como parametro el nombre del personaje, que es la parte variable del URL
     * @return un response de tipo AmiiboResponse
     */
    suspend fun getAmiiboByName(@Url url: String): Response<AmiiboResponse>
}