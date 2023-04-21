package com.swensonhe.common.entities

import com.google.gson.annotations.SerializedName

data class LocationWeather(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current,
    @SerializedName("forecast") val forecast: Forecast
)