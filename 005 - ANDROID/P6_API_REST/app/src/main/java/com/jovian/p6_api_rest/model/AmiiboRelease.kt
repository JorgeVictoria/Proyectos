package com.jovian.p6_api_rest.model

//Dentro de los objetos Amiibo, hay un campo de tipo array que recoge la fecha de lanzamiento
//de los Amiibos en distintas zonas mundiales.
//Tal como está escrito en el json, parece un clase donde cada zona es un atributo de la clase
data class AmiiboRelease(
    var au: String,
    var eu: String,
    var jap: String,
    var na: String
)
