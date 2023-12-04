package com.example.mvvm.utils.extentions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.net.wifi.WifiManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException
import java.nio.ByteOrder

internal fun Context.getCompactColor(@ColorRes color: Int?) =
    kotlin.run { ContextCompat.getColor(this, color ?: 0) }

internal fun Context.getCompactDrawable(@DrawableRes drawableId: Int?) =
    kotlin.run { ContextCompat.getDrawable(this, drawableId ?: 0) }

internal fun Context.getFont(font: Int): Typeface? = kotlin.run {
    return ResourcesCompat.getFont(this, font)
}

internal fun Context.showToast(message: String?) {
    message?.trim()?.let {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }
}

internal fun FragmentActivity.hideKeyboard(view: View? = null) {
    (view ?: window.currentFocus)?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

internal fun FragmentActivity.openKeyboard(view: View? = null) {
    (view ?: window.currentFocus)?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
    }
}

internal fun Context.copyToClipboard(textToCopy: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("App.Lable", textToCopy)
    clipboard.setPrimaryClip(clip)
    showToast("Text copied to clipboard")
}

fun Context.getIpAddressId(): String {
    var ipAddress = (getSystemService(Context.WIFI_SERVICE) as WifiManager).connectionInfo.ipAddress
    if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
        ipAddress = Integer.reverseBytes(ipAddress)
    }
    val ipByteArray = BigInteger.valueOf(ipAddress.toLong()).toByteArray()
    val ipAddressString: String? = try {
        InetAddress.getByAddress(ipByteArray).hostAddress
    } catch (ex: UnknownHostException) {
        null
    }
    return ipAddressString ?: "0.0.0.0"
}

internal fun Context.printApiError(message: String?) {
    Log.e("@@@@", "printApiError: ${message?.trim().toString()}")
}