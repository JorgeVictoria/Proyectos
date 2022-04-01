package com.jovian.mvvm.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(

    //le ponemos serialized si queremos trabajar con otro nombre
    @SerializedName("quote") val quote: String,
    @SerializedName("author")val author: String
)
