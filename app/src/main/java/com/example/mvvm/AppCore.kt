package com.example.mvvm

import android.app.Application

class AppCore : Application() {

    companion object {
        var instance: AppCore? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}