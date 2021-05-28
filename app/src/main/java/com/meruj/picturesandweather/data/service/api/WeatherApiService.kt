package com.meruj.picturesandweather.data.service.api

import com.meruj.picturesandweather.data.model.weather.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun get(@Query("appid") appID: String, @Query("id") countryID: Int,
            @Query("lang") lang: String, @Query("units") units: String): Weather
}