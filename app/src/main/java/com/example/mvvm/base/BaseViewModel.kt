package com.example.mvvm.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.mvvm.utils.extentions.isNetworkConnected

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var mContext: Context = application.applicationContext

    fun isInternetConnected(): Boolean {
        return mContext.isNetworkConnected()
    }

}