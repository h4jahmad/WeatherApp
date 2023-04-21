package com.swensonhe.common.entities

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday") val forecastDay: ArrayList<ForecastDay>,
)