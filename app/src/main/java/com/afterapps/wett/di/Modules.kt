package com.afterapps.wett.di

import androidx.room.Room
import com.afterapps.wett.data.database.DATABASE_NAME
import com.afterapps.wett.data.database.WeatherDatabase
import com.afterapps.wett.data.remote.OpenWeatherMapApi
import com.afterapps.wett.data.repository.WeatherRepository
import com.afterapps.wett.ui.home.HomeWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { OpenWeatherMapApi().createOpenWeatherMapApiService() }
    single { Room.databaseBuilder(get(), WeatherDatabase::class.java, DATABASE_NAME).build() }
    single { WeatherRepository(weatherDatabase = get(), weatherApi = get()) }
}

val homeModule = module { viewModel { HomeWeatherViewModel(weatherRepository = get()) } }