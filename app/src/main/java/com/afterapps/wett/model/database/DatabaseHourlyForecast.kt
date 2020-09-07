package com.afterapps.wett.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afterapps.wett.model.domain.HourlyForecast
import kotlin.math.roundToInt

@Entity
data class DatabaseHourlyForecast(
    @PrimaryKey
    val timestamp: Long,
    val temp: Double,
//    val uvi: Double, todo: handle uvi from current weather
    val windSpeed: Double,
    val dewPoint: Double,
    val humidity: Int,
    val pressure: Int,
//    val chanceOfRain: Double,todo: handle property name `1h`
    val icon: String,
    val description: String,
    val visibility: Int
)

fun DatabaseHourlyForecast.asDomainHourlyForecast() = HourlyForecast(
    timestamp = timestamp,
    temp = temp.roundToInt(),
    windSpeed = windSpeed,
    dewPoint = dewPoint,
    humidity = humidity,
    pressure = pressure,
    icon =icon,
    description = description,
    visibility = visibility
)