package com.jovian.chat01.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun timeStamp(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("dd/mm/yyyy HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return time
    }
}