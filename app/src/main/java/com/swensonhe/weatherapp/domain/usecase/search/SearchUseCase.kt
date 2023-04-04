package com.swensonhe.weatherapp.domain.usecase.search

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location

interface SearchUseCase {
    suspend fun searchCity(query: String): ActionResult<List<Location>>
}