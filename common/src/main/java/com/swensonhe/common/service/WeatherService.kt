package com.swensonhe.common.service

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast.json?key=${SearchService.API_KEY}")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("days") days: Int,
    ): ActionResult<LocationWeather>
}