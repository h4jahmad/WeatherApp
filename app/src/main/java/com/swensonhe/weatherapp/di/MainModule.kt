package com.swensonhe.weatherapp.di

import com.swensonhe.weatherapp.MainContracts
import com.swensonhe.weatherapp.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    abstract fun provideMainPresenter(presenter: MainPresenter): MainContracts.Presenter

    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        MainScope() + CoroutineName("WeatherApp")
}