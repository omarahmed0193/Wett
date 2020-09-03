package com.afterapps.wett.model.network

import com.afterapps.wett.model.database.DatabaseCurrentWeather

data class CurrentWeatherResponse(
    val current: Current
) {
    data class Current(
        val dt: Int,
        val temp: Double,
        val uvi: Double,
        val weather: List<Weather>
    ) {

        data class Weather(
            val description: String,
            val icon: String,
        )
    }
}

fun CurrentWeatherResponse.asDatabaseCurrentWeather() = DatabaseCurrentWeather(
    timestamp = current.dt.toLong(),
    temp = current.temp,
    icon = current.weather.firstOrNull()?.icon ?: "",
    description = current.weather.firstOrNull()?.description ?: ""
)