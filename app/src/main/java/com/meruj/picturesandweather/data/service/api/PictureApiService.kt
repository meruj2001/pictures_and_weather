package com.meruj.picturesandweather.data.service.api

import com.meruj.picturesandweather.data.model.Picture
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApiService {
    @GET("v2/list")
    suspend fun get(@Query("page") page: Int, @Query("limit") limit:Int): List<Picture>
}