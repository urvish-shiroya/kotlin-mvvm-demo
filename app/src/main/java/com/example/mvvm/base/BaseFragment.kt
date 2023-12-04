package com.example.mvvm.base

import android.os.Bundle
import com.example.mvvm.utils.extentions.isNetworkConnected

open class BaseFragment() : androidx.fragment.app.Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun isInternetConnected(): Boolean {
        return requireContext().isNetworkConnected()
    }

}