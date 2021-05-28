package com.meruj.picturesandweather.data.model.weather

data class MainInfo (
    var temp: Double,
    var temp_max: Double,
    var temp_min: Double,
    var feels_like: Double,
    var grnd_level: Int,
    var humidity: Int,
    var pressure: Int,
    var sea_level: Int,
)