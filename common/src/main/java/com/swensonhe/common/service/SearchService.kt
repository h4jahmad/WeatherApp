package com.swensonhe.common.service

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    companion object {
        // I'd add the API_KEY to my local gradle.properties and access it
        // from the a BuildConfig field. For the sake of this assignment and to make you able
        // to build the project, I decided to add the API_KEY here.
        const val API_KEY = "c5ae59fca62e4fa08fb143949231704"
    }

    @GET("search.json?key=$API_KEY")
    suspend fun searchCity(@Query("q") query: String): ActionResult<List<Location>>

}