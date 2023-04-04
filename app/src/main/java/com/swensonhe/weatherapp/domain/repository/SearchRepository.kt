package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location

interface SearchRepository {

    suspend fun searchCity(query: String): ActionResult<List<Location>>

}