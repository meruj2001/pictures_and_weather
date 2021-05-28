package com.meruj.picturesandweather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.meruj.picturesandweather.data.repository.WeatherRepository

class SignInViewModel : ViewModel() {

    companion object {
        const val WEATHER_COUNTRY_CODE = 498817
        const val WEATHER_UNITS = "metric"
        const val LANGUAGE = "ru"
    }

    private val weatherRepository = WeatherRepository()

    fun getWeather() =  weatherRepository.getWeather(WEATHER_COUNTRY_CODE, WEATHER_UNITS, LANGUAGE)
}