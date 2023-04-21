package com.swensonhe.common.service

import android.content.Context
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * This service facilitates image caching.
 * */
class ImageService @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun enqueue(url: String) {
        imageLoader.enqueue(getImageRequest(url))
    }

    private fun getImageRequest(url: String) = ImageRequest.Builder(context).apply {
        data(url)
        memoryCachePolicy(CachePolicy.DISABLED)
    }.build()

    private val imageLoader = context.imageLoader
}