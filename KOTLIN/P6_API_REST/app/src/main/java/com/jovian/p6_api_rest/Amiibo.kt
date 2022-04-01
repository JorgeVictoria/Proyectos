package com.jovian.p6_api_rest

data class Amiibo(
    var amiiboSeries: String,
    var character: String,
    var gameSeries: String,
    var head: String,
    var image: String,
    var name: String,
    //var release: ArrayList<AmiiboRelease>,
    var tail: String,
    var type: String
)
