package com.jovian.dogsearch

import com.google.gson.annotations.SerializedName

/**
 * el json que vamos a leer tiene los campos:
 * status: si la lectura ha sido correcta
 * message: es la URL de la imagen que vamos a cargar
 */
data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)
