package com.afterapps.wett.util

import java.util.*

fun getStartOfDayTimestamp(): Long {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.set(year, month, day, 0, 0, 0)
    return calendar.timeInMillis
}

fun getEndOfDayTimestamp(): Long {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.set(year, month, day, 23, 59, 59)
    return calendar.timeInMillis
}

fun getEndOfWeekTimestamp(): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, 6)
    return calendar.timeInMillis
}