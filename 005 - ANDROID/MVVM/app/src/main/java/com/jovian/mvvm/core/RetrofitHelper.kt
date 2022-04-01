package com.jovian.mvvm.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//para apuntar al retrofit de donde queremos leer los datos
object RetrofitHelper {

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}