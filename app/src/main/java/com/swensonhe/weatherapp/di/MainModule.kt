package com.swensonhe.weatherapp.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.swensonhe.weatherapp.MainContracts
import com.swensonhe.weatherapp.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.*

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    abstract fun provideMainPresenter(presenter: MainPresenter): MainContracts.Presenter

    companion object {
        @Provides
        fun provideMainActivityLifecycleOwner(
            activity: FragmentActivity,
        ): LifecycleOwner = activity

        @Provides
        fun provideLocale(): Locale = Locale.getDefault()
    }
}