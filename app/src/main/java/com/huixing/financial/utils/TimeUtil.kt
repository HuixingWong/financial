package com.huixing.financial.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


object TimeUtil {

    fun getDateStr(year: Int, month: Int, day: Int): String {
        val time = Calendar.getInstance().apply { set(year, month, day) }.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(time)
    }

}

@SuppressLint("NewApi")
fun String.toTimestamp(): Long {
    return try {
        val l = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    }catch (e: Exception){
        0
    }
}