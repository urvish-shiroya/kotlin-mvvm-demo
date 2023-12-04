package com.example.mvvm.utils.extentions

import android.view.View

internal fun View.visible() = run {
    this.visibility = View.VISIBLE
}

internal fun View.gone() = run {
    this.visibility = View.GONE
}

internal fun View.invisible() = run {
    this.visibility = View.INVISIBLE
}

internal fun View.isVisible() = this.visibility == View.VISIBLE
internal fun View.isGone() = this.visibility == View.GONE
internal fun View.isInvisible() = this.visibility == View.INVISIBLE