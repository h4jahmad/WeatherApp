package com.swensonhe.weatherapp.di

import com.swensonhe.common.di.RemoteDataSource
import com.swensonhe.weatherapp.domain.datasource.SearchDataSource
import com.swensonhe.weatherapp.domain.datasource.SearchRemoteDataSource
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
    fun bindsSearchRemoteDataSource(dataSource: SearchRemoteDataSource): SearchDataSource

    @Binds
    fun bindsSearchRepository(impl: SearchRepositoryImpl): SearchRepository

}