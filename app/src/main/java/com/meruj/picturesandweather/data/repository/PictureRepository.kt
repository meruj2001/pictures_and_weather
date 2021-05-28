package com.meruj.picturesandweather.data.repository

import androidx.lifecycle.liveData
import com.meruj.picturesandweather.data.service.RetrofitBuilder
import com.meruj.picturesandweather.data.service.api.PictureApiService
import com.meruj.picturesandweather.util.Resource
import kotlinx.coroutines.Dispatchers

class PictureRepository {

    private val pictureApiService: PictureApiService = RetrofitBuilder().getPicturesApiService()

    fun getPicture(page: Int, limit: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = pictureApiService.get(page, limit)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}


