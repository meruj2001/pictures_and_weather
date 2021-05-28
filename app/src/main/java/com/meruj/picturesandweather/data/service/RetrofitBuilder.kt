package com.meruj.picturesandweather.data.service

import com.google.gson.GsonBuilder
import com.meruj.picturesandweather.data.service.api.PictureApiService
import com.meruj.picturesandweather.data.service.api.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        val PICTURE_BASE_URL: String = "https://picsum.photos/"
        val WEATHER_BASE_URL: String = "https://api.openweathermap.org/data/2.5/"
    }

    private fun getRetrofit(baseUrl:String): Retrofit {
        val loggingInterceptor = getLoggingInterceptor()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory( GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            ))
            .client(getHttpClient(loggingInterceptor))
            .build()

    }


    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }


    private fun getHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    fun getPicturesApiService()= getRetrofit(PICTURE_BASE_URL).create(PictureApiService::class.java)

    fun getWeatherApiService()= getRetrofit(WEATHER_BASE_URL).create(WeatherApiService::class.java)

}