package com.meruj.picturesandweather.data.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RainInfo (
    @SerializedName("1h")
    @Expose(serialize = false)
    var oneh: Double,
    @SerializedName("3h")
    @Expose(serialize = false)
    var treeh: Double
)