package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location

interface SearchDataSource {
    suspend fun search(query: String): ActionResult<List<Location>>
}