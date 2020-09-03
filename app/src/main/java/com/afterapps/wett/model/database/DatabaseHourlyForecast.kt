package com.afterapps.wett.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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