package com.example.mvvm.utils.extentions

import android.util.Base64
import java.nio.charset.StandardCharsets

fun String?.encode(): String = kotlin.run {
    if (this.isNullOrEmpty().not()) {
        val data = this.toString().toByteArray(StandardCharsets.UTF_8)
        return Base64.encodeToString(data, Base64.DEFAULT)
    }
    return ""
}

fun String?.decode(): String = kotlin.run {
    if (this.isNullOrEmpty().not()) {
        val data: ByteArray = Base64.decode(this, Base64.DEFAULT)
        return String(data, StandardCharsets.UTF_8)
    }
    return ""
}