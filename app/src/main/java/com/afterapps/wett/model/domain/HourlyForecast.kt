package com.afterapps.wett.model.domain

data class HourlyForecast(
    val timestamp: Long,
    val temp: Double,
    val uvi: Double,
    val windSpeed: Double,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Int,
    val chanceOfRain: Double,
    val icon: String,
    val description: String,
    val visibility: Double
)