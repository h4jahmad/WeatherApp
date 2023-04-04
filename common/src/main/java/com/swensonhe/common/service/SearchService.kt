package com.swensonhe.common.service

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    companion object {
        const val API_KEY = "a1602c49eec0433d87293100230204"
    }

    @GET("search.json?key=$API_KEY")
    suspend fun searchCity(@Query("q") query: String): ActionResult<List<Location>>

}