package com.swensonhe.weatherapp.data.datasource.search

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.common.service.SearchService
import javax.inject.Inject

class SearchCityRemoteDataSource @Inject constructor(
    private val service: SearchService
): SearchCityDataSource {
    suspend fun search(query: String): ActionResult<List<Location>> {

    }
}