package com.swensonhe.weatherapp.di

import com.swensonhe.weatherapp.domain.usecase.datetime.DateTimeUseCase
import com.swensonhe.weatherapp.domain.usecase.datetime.DateTimeUseCaseImpl
import com.swensonhe.weatherapp.domain.usecase.search.SearchUseCase
import com.swensonhe.weatherapp.domain.usecase.search.SearchUseCaseImpl
import com.swensonhe.weatherapp.domain.usecase.weather.ForecastUseCase
import com.swensonhe.weatherapp.domain.usecase.weather.ForecastUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface UseCaseModule {

    @Binds
    fun bindDateTimeUseCase(impl: DateTimeUseCaseImpl): DateTimeUseCase

    @Binds
    fun bindSearchUseCase(impl: SearchUseCaseImpl): SearchUseCase

    @Binds
    fun bindForecastUseCase(impl: ForecastUseCaseImpl): ForecastUseCase
}