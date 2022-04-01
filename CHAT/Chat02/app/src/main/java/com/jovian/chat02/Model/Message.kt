package com.jovian.chat02.model

/**
 * clase que almacena objetos de tipo mensaje
 * Basicamente cada objeto tiene tres atributos de tipo String
 * el mensaje
 * el id que identifica si es un mensaje entrante o saliente
 * y un atributo para almacenar la fecha/hora del mensaje
 */
data class Message(

    val message: String,
    val id: String,
    val time: String
)
