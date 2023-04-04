package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.common.service.SearchService
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val service: SearchService
): SearchDataSource {
    override suspend fun search(query: String): ActionResult<List<Location>> =
        service.searchCity(query)
}