package com.afterapps.wett.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseDailyForecast(
    @PrimaryKey
    val timestamp: Long,
    val sunrise: Long,
    val sunset: Long,
    val tempMin: Double,
    val tempMax: Double
)