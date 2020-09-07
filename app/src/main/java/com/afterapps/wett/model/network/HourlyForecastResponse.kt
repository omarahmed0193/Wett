package com.afterapps.wett.model.network

import com.afterapps.wett.model.database.DatabaseHourlyForecast

data class HourlyForecastResponse(
    val hourly: List<Hourly>
) {
    data class Hourly(
        val dew_point: Double,
        val dt: Int,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        val visibility: Int,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_speed: Double
    ) {

        data class Weather(
            val description: String,
            val icon: String
        )
    }
}

fun List<HourlyForecastResponse.Hourly>.asDatabaseHourlyForecast() = map {
    DatabaseHourlyForecast(
        timestamp = it.dt.toLong()* 1000,
        temp = it.temp,
        windSpeed = it.wind_speed,
        dewPoint = it.dew_point,
        humidity = it.humidity,
        pressure = it.pressure,
        icon = it.weather.firstOrNull()?.icon ?: "",
        description = it.weather.firstOrNull()?.description ?: "",
        visibility = it.visibility
    )
}