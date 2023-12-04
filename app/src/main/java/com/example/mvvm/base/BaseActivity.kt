package com.example.mvvm.base

import android.os.Bundle
import android.os.PersistableBundle
import com.example.mvvm.utils.extentions.isNetworkConnected

open class BaseActivity() : androidx.appcompat.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun isInternetConnected(): Boolean {
        return this@BaseActivity.isNetworkConnected()
    }

}