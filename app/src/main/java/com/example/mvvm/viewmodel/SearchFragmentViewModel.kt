package com.example.mvvm.viewmodel

import android.app.Application
import com.example.mvvm.base.BaseViewModel

class SearchFragmentViewModel(application: Application) : BaseViewModel(application) {

    private var apiData: String? = null

    fun fetchApiIfNeeded(): String {
        if (apiData == null) {
            apiData = "ApiData was Initialized"
        }
        return apiData ?: "Not Initialized"
    }

}