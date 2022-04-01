package com.jovian.mvvm.model

import com.google.gson.annotations.SerializedName

//clase que almacena citas y su autor. Lo vamos a utilizar para leer los datos del retrofit
data class QuoteModel(
    @SerializedName("quote") val quote:String,
    @SerializedName ("author") val author:String
)
