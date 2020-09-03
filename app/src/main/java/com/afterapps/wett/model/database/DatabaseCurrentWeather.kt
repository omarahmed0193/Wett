package com.afterapps.wett.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseCurrentWeather(
    @PrimaryKey
    val id: Int? = 1,
    val timestamp: Long,
    val temp: Double,
    val icon: String,
    val description: String
)