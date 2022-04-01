package com.jovian.p6_api_rest.model

//modelo de datos. Recoge cada uno de los elementos que forman parte de un objeto Amiibo
//Recordemos que el Json es un array o lista de objetos Amiibo
data class Amiibo(
    var amiiboSeries: String,
    var character: String,
    var gameSeries: String,
    var head: String,
    var image: String,
    var name: String,
    var release: AmiiboRelease,
    var tail: String,
    var type: String
)
