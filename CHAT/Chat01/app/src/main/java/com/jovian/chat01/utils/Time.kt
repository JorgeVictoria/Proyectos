package com.jovian.chat01.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

//clase para recoger la fecha/hora y transformar diche info en una cadena de tipo STRING
object Time {

    fun timeStamp() : String {

        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return time.toString()

    }


}