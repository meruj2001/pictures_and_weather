package com.meruj.picturesandweather.data.model.weather

data class WeatherInfo(
    var id: Int,
    var description: String,
    var icon: String,
    var main: String
)