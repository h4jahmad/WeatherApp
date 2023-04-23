package com.swensonhe.weatherapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.swensonhe.weatherapp.model.Coordinates
import kotlin.math.max

typealias OnItemClicked<T> = (T) -> Unit

val DEFAULT_LOCATION_COORDINATES = Coordinates(
    lat = 37.78,
    lon = -122.42
)

fun View.circleReveal(
    startView: View
) {
    val cx = (startView.left + startView.right) / 2
    val cy = (startView.top + startView.bottom) / 2
    val endRadius = max(width, height)
    val anim = ViewAnimationUtils.createCircularReveal(
        this,
        cx,
        cy,
        0f,
        endRadius.toFloat()
    )
    anim.duration = 300
    isVisible = true
    anim.start()
}

fun View.circleHide(
    endView: View,
    onAnimationEnd: () -> Unit = {},
) {
    val cx = (endView.left + endView.right) / 2
    val cy = (endView.top + endView.bottom) / 2

    val endRadius = max(width, height)
    val anim = ViewAnimationUtils.createCircularReveal(
        this,
        cx,
        cy,
        endRadius.toFloat(),
        0f
    )
    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            isVisible = false
            onAnimationEnd()
        }
    })
    anim.start()
}

fun Context.isOnTablet() =
    ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)

fun View.rotate(onAnimationEnds: () -> Unit) {
    val anim = AnimationUtils.loadAnimation(
        context,
        R.anim.anim_rotate
    )
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnds()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    startAnimation(anim)
}