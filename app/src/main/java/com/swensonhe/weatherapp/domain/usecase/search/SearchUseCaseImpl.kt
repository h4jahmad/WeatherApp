package com.swensonhe.weatherapp.domain.usecase.search

import com.swensonhe.common.di.IoDispatcher
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.weatherapp.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) : SearchUseCase {
    override suspend fun searchCity(
        query: String,
    ): ActionResult<List<Location>> = withContext(dispatcher) {
        searchRepository.searchCity(query)
    }
}