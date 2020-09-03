package com.afterapps.wett.model.domain

data class DailyForecast(
    val timestamp: Long,
    val sunrise: Long,
    val sunset: Long,
    val tempMin: Double,
    val tempMax: Double
)