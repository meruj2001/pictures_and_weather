package com.meruj.picturesandweather.data.repository

import androidx.lifecycle.liveData
import com.meruj.picturesandweather.data.service.RetrofitBuilder
import com.meruj.picturesandweather.data.service.api.WeatherApiService
import com.meruj.picturesandweather.util.Resource
import kotlinx.coroutines.Dispatchers

class WeatherRepository {
    private val weatherApiService: WeatherApiService = RetrofitBuilder().getWeatherApiService()

    companion object {
        const val WEATHER_APPID: String = "c35880b49ff95391b3a6d0edd0c722eb"
    }

    fun getWeather(
        countryCode: Int, weatherUnit: String,
        language: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = weatherApiService.get(
                        WEATHER_APPID,
                        countryCode, language, weatherUnit
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}