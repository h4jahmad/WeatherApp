package com.swensonhe.weatherapp.domain.usecase.weather

import com.swensonhe.common.di.IoDispatcher
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.weatherapp.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) : ForecastUseCase {

}