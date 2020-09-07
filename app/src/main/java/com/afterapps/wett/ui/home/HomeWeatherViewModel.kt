package com.afterapps.wett.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.afterapps.wett.data.repository.WeatherRepository
import com.afterapps.wett.model.database.asDomainHourlyForecast
import com.afterapps.wett.model.domain.HourlyForecast
import com.afterapps.wett.util.PreferencesHelper
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeWeatherViewModel(
    weatherRepository: WeatherRepository,
    private val preferenceHelper: PreferencesHelper
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val currentWeather = weatherRepository.currentWeather
    val hourlyWeather = weatherRepository.hourlyWeather
    val dailyWeather = weatherRepository.dailyWeather

    val selectedHour = LiveEvent<HourlyForecast>()
    val isUnitMetric = LiveEvent<Boolean>()


    //todo: remove those calls from here and add them to worker
    init {
        isUnitMetric.value = preferenceHelper.isUnitMetric()
        viewModelScope.launch {
            val dummyLat = 30.038235
            val dummyLng = 31.260525
            weatherRepository.getCurrentWeather(dummyLat, dummyLng)
            weatherRepository.getHourlyForecast(dummyLat, dummyLng)
            weatherRepository.getDailyForecast(dummyLat, dummyLng)
        }
    }

    fun onSeekbarIndexChange(index: Int) {
        hourlyWeather.value?.let {
            if (index < it.size) {
                selectedHour.value = it[index].asDomainHourlyForecast()
            }
        }
    }

    fun onChangeUnitClick() {
        preferenceHelper.changeUnit()
        isUnitMetric.value = preferenceHelper.isUnitMetric()
    }
}