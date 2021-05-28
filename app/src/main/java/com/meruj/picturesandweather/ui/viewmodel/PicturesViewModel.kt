package com.meruj.picturesandweather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.meruj.picturesandweather.data.repository.PictureRepository

class PicturesViewModel : ViewModel() {
    private var pictureRepository = PictureRepository()
    private var pagerNum = 1

    fun getPictures() = pictureRepository.getPicture(pagerNum, 20)

    fun loadNextItems() {
        pagerNum++
    }

    fun loadPrewItems() {
        if (pagerNum > 1) pagerNum--
    }

}
