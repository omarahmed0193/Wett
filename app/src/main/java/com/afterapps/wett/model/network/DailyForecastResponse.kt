package com.afterapps.wett.model.network

import com.afterapps.wett.model.database.DatabaseDailyForecast

data class DailyForecastResponse(
    val daily: List<Daily>
) {
    data class Daily(
        val dt: Int,
        val sunrise: Int,
        val sunset: Int,
        val temp: Temp,
    ) {
        data class Temp(
            val max: Double,
            val min: Double,
        )
    }
}

fun List<DailyForecastResponse.Daily>.asDatabaseDailyForecast() = map {
    DatabaseDailyForecast(
        timestamp = it.dt.toLong()* 1000,
        sunrise = it.sunrise.toLong()* 1000,
        sunset = it.sunset.toLong()* 1000,
        tempMin = it.temp.min,
        tempMax = it.temp.max
    )
}