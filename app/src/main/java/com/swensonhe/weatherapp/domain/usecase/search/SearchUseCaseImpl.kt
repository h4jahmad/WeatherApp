package com.swensonhe.weatherapp.domain.usecase.search

import com.swensonhe.common.di.IoDispatcher
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SearchUseCase {
    override suspend fun searchCity(query: String): ActionResult<List<Location>> {
        TODO("Not yet implemented")
    }
}