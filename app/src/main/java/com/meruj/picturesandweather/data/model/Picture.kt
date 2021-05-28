package com.meruj.picturesandweather.data.model

data class Picture(
    val id: String,
    val url: String,
    val author: String,
    val download_url: String,
    val height: Int,
    val width: Int
)
