package com.swensonhe.weatherapp.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.swensonhe.common.di.IsOnTablet
import com.swensonhe.weatherapp.MainContracts
import com.swensonhe.weatherapp.MainPresenter
import com.swensonhe.weatherapp.isOnTablet
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

        /**
         * I used this [IsOnTablet] is order to prevent any confusions in case of injecting
         * other objects of the type [Boolean].
         * */
        @IsOnTablet
        @Provides
        fun isOnTablet(@ApplicationContext context: Context): Boolean = context.isOnTablet()
    }
}