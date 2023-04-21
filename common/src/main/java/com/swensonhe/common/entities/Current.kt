package com.swensonhe.common.entities

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated_epoch") var lastUpdatedEpoch: Int,
    @SerializedName("last_updated") var lastUpdated: String,
    @SerializedName("temp_c") var tempC: Double,
    @SerializedName("temp_f") var tempF: Double,
    @SerializedName("is_day") var isDay: Int,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("wind_mph") var windMph: Double,
    @SerializedName("wind_kph") var windKph: Double,
    @SerializedName("wind_degree") var windDegree: Int,
    @SerializedName("wind_dir") var windDir: String,
    @SerializedName("pressure_mb") var pressureMb: Double,
    @SerializedName("pressure_in") var pressureIn: Double,
    @SerializedName("precip_mm") var precipMm: Double,
    @SerializedName("precip_in") var precipIn: Double,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("cloud") var cloud: Int,
    @SerializedName("feelslike_c") var feelslikeC: Double,
    @SerializedName("feelslike_f") var feelslikeF: Double,
    @SerializedName("vis_km") var visKm: Double,
    @SerializedName("vis_miles") var visMiles: Double,
    @SerializedName("uv") var uv: Double,
    @SerializedName("gust_mph") var gustMph: Double,
    @SerializedName("gust_kph") var gustKph: Double,
)