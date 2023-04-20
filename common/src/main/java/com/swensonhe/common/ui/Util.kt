package com.swensonhe.common.ui

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(
    @StringRes messageResId: Int? = null,
    message: String? = null
) {
    when {
        messageResId != null -> Snackbar.make(this, messageResId, Snackbar.LENGTH_LONG)
        message != null -> Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        else -> throw IllegalStateException("message has not been fed in neither of parameters.")
    }.show()
}