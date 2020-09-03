package com.afterapps.wett.data.repository

import com.afterapps.wett.data.database.WeatherDao
import com.afterapps.wett.data.database.WeatherDatabase
import com.afterapps.wett.data.remote.OpenWeatherMapApi
import com.afterapps.wett.model.network.asDatabaseCurrentWeather
import com.afterapps.wett.model.network.asDatabaseDailyForecast
import com.afterapps.wett.model.network.asDatabaseHourlyForecast
import com.afterapps.wett.util.getEndOfDayTimestamp
import com.afterapps.wett.util.getEndOfWeekTimestamp
import com.afterapps.wett.util.getStartOfDayTimestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val weatherDatabase: WeatherDatabase,
    private val weatherApi: OpenWeatherMapApi.ApiService
) {

    val currentWeather
        get() = weatherDatabase.weatherDao.getCurrentWeather()

    val hourlyWeather
        get() = weatherDatabase.weatherDao.getTodayHourlyForecast(
            getStartOfDayTimestamp(),
            getEndOfDayTimestamp()
        )

    val dailyWeather
        get() = weatherDatabase.weatherDao.getDailyForecast(
            getStartOfDayTimestamp(),
            getEndOfWeekTimestamp()
        )

    suspend fun getCurrentWeather(lat: Double, lng: Double) {
        withContext(Dispatchers.IO) {
            val currentWeather = weatherApi.getCurrentWeather(lat, lng).asDatabaseCurrentWeather()
            weatherDatabase.weatherDao.insertCurrentWeather(currentWeather)
        }
    }

    suspend fun getHourlyForecast(lat: Double, lng: Double) {
        withContext(Dispatchers.IO) {
            val hourlyForecast =
                weatherApi.getHourlyForecastForTwoDays(lat, lng).hourly.asDatabaseHourlyForecast()
            weatherDatabase.weatherDao.insertHourlyForecast(hourlyForecast)
        }
    }

    suspend fun getDailyForecast(lat: Double, lng: Double) {
        withContext(Dispatchers.IO) {
            val dailyForecast =
                weatherApi.getDailyForecastForFiveDays(lat, lng).daily.asDatabaseDailyForecast()
            weatherDatabase.weatherDao.insertDailyForecast(dailyForecast)
        }
    }
}