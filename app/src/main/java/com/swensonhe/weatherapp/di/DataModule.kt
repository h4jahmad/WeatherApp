package com.swensonhe.weatherapp.di

import com.swensonhe.common.di.RemoteDataSource
import com.swensonhe.weatherapp.domain.datasource.ForecastDataSource
import com.swensonhe.weatherapp.domain.datasource.ForecastRemoteDataSource
import com.swensonhe.weatherapp.domain.datasource.SearchDataSource
import com.swensonhe.weatherapp.domain.datasource.SearchRemoteDataSource
import com.swensonhe.weatherapp.domain.repository.ForecastRepository
import com.swensonhe.weatherapp.domain.repository.ForecastRepositoryImpl
import com.swensonhe.weatherapp.domain.repository.SearchRepository
import com.swensonhe.weatherapp.domain.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface DataModule {

    @Binds
    @RemoteDataSource
    fun bindSearchRemoteDataSource(dataSource: SearchRemoteDataSource): SearchDataSource

    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    @RemoteDataSource
    fun bindForecastRemoteDataSource(dataSource: ForecastRemoteDataSource): ForecastDataSource

    @Binds
    fun bindForecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

}