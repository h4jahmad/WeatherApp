package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.di.RemoteDataSource
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.weatherapp.domain.datasource.SearchDataSource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun searchCity(query: String): ActionResult<List<Location>> =
        remoteDataSource.search(query)

}