package com.afterapps.wett.model.domain

data class CurrentWeather(
    val timestamp: Long,
    val temp: Double,
    val icon: String,
    val description: String
)