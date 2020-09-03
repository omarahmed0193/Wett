package com.afterapps.wett.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.afterapps.wett.model.database.DatabaseCurrentWeather
import com.afterapps.wett.model.database.DatabaseDailyForecast
import com.afterapps.wett.model.database.DatabaseHourlyForecast
import com.afterapps.wett.model.domain.CurrentWeather
import com.afterapps.wett.model.domain.DailyForecast
import com.afterapps.wett.model.domain.HourlyForecast


const val DATABASE_VERSION = 1
const val DATABASE_NAME = "WeatherDatabase"

@Dao
interface WeatherDao {
    //current weather queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(databaseWeather: DatabaseCurrentWeather)

    @Query("select * from databasecurrentweather")
    fun getCurrentWeather(): LiveData<DatabaseCurrentWeather>

    //hourly weather forecast queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyForecast(databaseHourlyWeather: List<DatabaseHourlyForecast>)

    @Query("select * from databasehourlyforecast WHERE timestamp BETWEEN :dayStart AND :dayEnd")
    fun getTodayHourlyForecast(dayStart: Long, dayEnd: Long): LiveData<List<DatabaseHourlyForecast>>


    //daily weather forecast queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyForecast(databaseHourlyWeather: List<DatabaseDailyForecast>)

    @Query("select * from databasedailyforecast WHERE timestamp BETWEEN :startDate AND :endDate")
    fun getDailyForecast(startDate: Long, endDate: Long): LiveData<List<DatabaseDailyForecast>>
}

@Database(
    entities = [DatabaseCurrentWeather::class, DatabaseHourlyForecast::class, DatabaseDailyForecast::class],
    version = DATABASE_VERSION
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}