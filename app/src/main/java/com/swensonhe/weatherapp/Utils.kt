package com.swensonhe.weatherapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.view.isVisible
import kotlin.math.hypot
import kotlin.math.max

typealias OnItemClicked<T> = (T) -> Unit

const val format12Hours = "h:mm a"

fun View.circleReveal(
    left: Int,
    bottom: Int,
) {
    val cx = width / 2
    val cy = height / 2
    val finalRadius = hypot(
        cx.toDouble(),
        cy.toDouble()
    ).toFloat()
    val startRadius = 0
    val endRadius = max(width, height)
    val anim = ViewAnimationUtils.createCircularReveal(
        this,
        cx,
        cy,
        0f,
        finalRadius
    )
    isVisible = true
    anim.start()
}

fun View.circleHide() {
    val cx = width / 2
    val cy = height / 2

    // get the initial radius for the clipping circle
    val initialRadius = hypot(
        cx.toDouble(),
        cy.toDouble()
    ).toFloat()
    val anim = ViewAnimationUtils.createCircularReveal(
        this,
        cx,
        cy,
        initialRadius,
        0f
    )
    anim.addListener(object : AnimatorListenerAdapter() {

        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            isVisible = false
        }
    })
    anim.start()
}