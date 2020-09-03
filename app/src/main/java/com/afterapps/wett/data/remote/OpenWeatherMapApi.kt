package com.afterapps.wett.data.remote

import com.afterapps.wett.BuildConfig
import com.afterapps.wett.model.network.CurrentWeatherResponse
import com.afterapps.wett.model.network.DailyForecastResponse
import com.afterapps.wett.model.network.HourlyForecastResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

//Base url for OpenWeather API
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

//Api key query parameter name for okhttp interceptor
private const val API_KEY_PARAMETER = "appid"

//OpenWeather API key
private const val API_KEY = BuildConfig.OPEN_WEATHER_API_KEY

//Timeout interval in seconds
private const val TIMEOUT_INTERVAL = 30L

//Default unit
private const val DEFAULT_UNIT = "metric"

//Excluded queries
private const val CURRENT_WEATHER_QUERY = "hourly,daily,minutely"
private const val HOURLY_FORECAST_QUERY = "current,daily,minutely"
private const val DAILY_FORECAST_QUERY = "current,hourly,minutely"

class OpenWeatherMapApi {

    interface ApiService {


        @GET("onecall")
        suspend fun getCurrentWeather(
            @Query("lat") lat: Double,
            @Query("lon") lng: Double,
            @Query("exclude") excludeQuery: String? = CURRENT_WEATHER_QUERY,
            @Query("units") unit: String? = DEFAULT_UNIT
        ): CurrentWeatherResponse

        @GET("onecall")
        suspend fun getHourlyForecastForTwoDays(
            @Query("lat") lat: Double,
            @Query("lon") lng: Double,
            @Query("exclude") excludeQuery: String? = HOURLY_FORECAST_QUERY,
            @Query("units") unit: String? = DEFAULT_UNIT
        ): HourlyForecastResponse

        @GET("onecall")
        suspend fun getDailyForecastForFiveDays(
            @Query("lat") lat: Double,
            @Query("lon") lng: Double,
            @Query("exclude") excludeQuery: String? = DAILY_FORECAST_QUERY,
            @Query("units") unit: String? = DEFAULT_UNIT
        ): DailyForecastResponse
    }

    fun createOpenWeatherMapApiService(): ApiService {

        //Initializing logging interceptor for HTTP requests debugging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor { chain ->

                var originalRequest = chain.request()

                //Adding api key parameter to each request via okhttp interceptor
                val newUrl = originalRequest.url.newBuilder()
                    .addQueryParameter(API_KEY_PARAMETER, API_KEY)
                    .build()

                originalRequest = originalRequest.newBuilder().url(newUrl).build()
                chain.proceed(originalRequest)
            }

            connectTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
            addNetworkInterceptor(interceptor)
        }.build()

        //Initializing moshi converter
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        //Initializing retroFit
        val retroFit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //Adding moshi converter
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

        return retroFit.create(ApiService::class.java)
    }
}