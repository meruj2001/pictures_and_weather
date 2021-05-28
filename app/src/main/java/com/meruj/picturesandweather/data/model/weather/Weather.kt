package com.meruj.picturesandweather.data.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    var id: Int,
    var dt: Int,
    var timezone: Int,
    var visibility: Int,
    var cod: Int,
    var name: String,
    var base: String,
    var coord: CoordInfo,
    var clouds: CloudsInfo,
    var main: MainInfo,
    @SerializedName("rain")
    @Expose(serialize = false)
    var rain: RainInfo,
    @SerializedName("snow")
    @Expose(serialize = false)
    var snow: SnowInfo,
    var sys: SysInfo,
    var wind: WindInfo,
    var weather: List<WeatherInfo>
)
