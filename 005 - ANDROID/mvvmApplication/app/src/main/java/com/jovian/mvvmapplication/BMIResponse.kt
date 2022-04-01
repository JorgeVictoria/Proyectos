package com.jovian.mvvmapplication

interface BMIResponse {

    fun onSuccess(result:Double)
    fun onHeightError(error:String)
    fun onWeightError(error:String)
    fun onError(error:String)
    fun onLoading(loading: Boolean)
}