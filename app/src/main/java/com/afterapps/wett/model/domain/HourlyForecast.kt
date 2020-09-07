package com.afterapps.wett.model.domain

data class HourlyForecast(
    val timestamp: Long,
    val temp: Int,
    val windSpeed: Double,
    val dewPoint: Double,
    val humidity: Int,
    val pressure: Int,
    val icon: String,
    val description: String,
    val visibility: Int
)