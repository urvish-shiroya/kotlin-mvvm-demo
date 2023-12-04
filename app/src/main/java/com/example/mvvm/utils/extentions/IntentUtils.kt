package com.inteletravel.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment


internal inline fun <reified T> Context.startActivity(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    intent.block()
    startActivity(intent)
}

internal inline fun <reified T> Fragment.startActivity(block: Intent.() -> Unit) {
    val intent = Intent(requireContext(), T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    intent.block()
    requireContext().startActivity(intent)
}

internal inline fun <reified T> Activity.startActivityForResult(
    requestCode: Int,
    block: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    intent.block()
    startActivityForResult(intent, requestCode)
}

internal inline fun <reified T> Fragment.startActivityForResult(
    requestCode: Int,
    block: Intent.() -> Unit
) {
    val intent = Intent(requireContext(), T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    intent.block()
    requireActivity().startActivityForResult(intent, requestCode)
}