package com.example.mvvm.utils.extentions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

internal fun Context.getCompactColor(@ColorRes color: Int?) =
    kotlin.run { ContextCompat.getColor(this, color ?: 0) }

internal fun Context.getCompactDrawable(@DrawableRes drawableId: Int?) =
    kotlin.run { ContextCompat.getDrawable(this, drawableId ?: 0) }

internal fun Context.showToast(message: String?) {
    message?.trim()?.let {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }
}

internal fun Context.printApiError(message: String?) {
    Log.e("@@@@", "printApiError: ${message?.trim().toString()}")
}