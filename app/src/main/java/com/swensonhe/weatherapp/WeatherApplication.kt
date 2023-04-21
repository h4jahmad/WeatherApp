package com.swensonhe.weatherapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication: Application(), ImageLoaderFactory {

    override fun newImageLoader() = ImageLoader.Builder(this)
        .diskCache {
            DiskCache.Builder()
                .directory(filesDir.resolve("images"))
                .build()
        }
        .respectCacheHeaders(true)
        .apply { if (BuildConfig.DEBUG) logger(DebugLogger()) }
        .build()
}