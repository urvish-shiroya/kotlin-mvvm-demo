package com.example.mvvm.utils.extentions

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.Timer
import java.util.TimerTask

inline fun EditText.doAfterTextChanged(
    delay: Long = 500, crossinline onTextChangedDelayed: (text: String) -> Unit
) = onTextChangeListener(delay, onTextChangedDelayed)

inline fun EditText.onTextChangeListener(
    delay: Long, crossinline onTextChangedDelayed: (text: String) -> Unit
): TextWatcher {

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            handlerPostDelayed(delay) { onTextChangedDelayed.invoke(s?.toString() ?: "") }
        }
    }
    this.addTextChangedListener(textWatcher)
    return textWatcher
}

var handlerDelayTimer: Timer = Timer()
inline fun handlerPostDelayed(delay: Long, crossinline onSuccess: () -> Unit) {
    handlerDelayTimer.cancel()
    handlerDelayTimer = Timer()
    handlerDelayTimer.schedule(object : TimerTask() {
        override fun run() {
            Handler(Looper.getMainLooper()).post {
                onSuccess.invoke()
            }
        }
    }, delay)
}

internal fun EditText.showKeyboard(context: Context) {
    val editText = this as EditText
    editText.requestFocus()
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}

internal fun EditText.isEmailValid(): Boolean {
    return TextUtils.isEmpty(this.text.toString())
        .not() && android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
}