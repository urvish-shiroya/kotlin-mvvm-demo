package com.example.mvvm.utils.extentions

fun generateExpiredTime(minutes: Long? = 1L): Long = run {
    return System.currentTimeMillis() + (minutes?.toInt()?.times(60))?.times(1000)?.toLong()!!
}

fun Long.isExpired(): Boolean = run {
    return (this <= 0) || (this - System.currentTimeMillis() <= 1)
}