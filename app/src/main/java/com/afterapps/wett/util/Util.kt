package com.afterapps.wett.util

import java.util.*
import kotlin.math.roundToInt

fun getCurrentHourTimestamp(): Long {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    calendar.set(year, month, day, hour, 0, 0)
    return calendar.timeInMillis
}

fun getTheNextTwentyFourHoursTimestamp(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = getCurrentHourTimestamp()
    calendar.add(Calendar.DAY_OF_MONTH, 1)
    return calendar.timeInMillis
}

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

fun convertCelsiusToFahrenheit(tempInCelsius: Int) = ((9 / 5.0 * tempInCelsius) + 32).roundToInt()
