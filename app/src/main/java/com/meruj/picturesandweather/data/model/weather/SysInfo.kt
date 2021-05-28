package com.meruj.picturesandweather.data.model.weather

data class SysInfo(
    var id: Int,
    var country: String,
    var sunrise: Long,
    var sunset: Long,
    var type: Int
)
