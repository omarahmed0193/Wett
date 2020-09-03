package com.afterapps.wett.ui.home

import androidx.lifecycle.ViewModel
import com.afterapps.wett.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeWeatherViewModel(weatherRepository: WeatherRepository) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val currentWeather = weatherRepository.currentWeather
    val hourlyWeather = weatherRepository.hourlyWeather
    val dailyWeather = weatherRepository.dailyWeather

    //todo: remove those calls from here and add them to worker
    init {
        viewModelScope.launch {
            val dummyLat = 30.038235
            val dummyLng = 31.260525
            weatherRepository.getCurrentWeather(dummyLat, dummyLng)
            weatherRepository.getHourlyForecast(dummyLat, dummyLng)
            weatherRepository.getDailyForecast(dummyLat, dummyLng)
        }
    }
}