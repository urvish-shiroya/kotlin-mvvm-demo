package com.example.mvvm.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvm.AppCore
import com.example.mvvm.models.Countries
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Store {

    private const val COUNTRIES_LIST = "countries.all.data"
    private const val EXPIRED_TIME = "countries.expired.time"

    private fun getPreferences(context: Context? = null): SharedPreferences? {
        return (context ?: AppCore.instance)?.getSharedPreferences("AppStore", Context.MODE_PRIVATE)
    }

    private fun getEditor(context: Context? = null): SharedPreferences.Editor? {
        return getPreferences(context ?: AppCore.instance)?.edit()
    }

    var expiredTime: Long?
        get() = getPreferences()?.getLong(EXPIRED_TIME, 0)
        set(value) = getEditor().let { it?.putLong(EXPIRED_TIME, value ?: 0)?.apply() }

    var countriesList: List<Countries>?
        get() = Gson().fromJson(getPreferences()?.getString(COUNTRIES_LIST, ""), object :
            TypeToken<List<Countries>>() {}.type)
        set(value) = getEditor().let {
            it?.putString(COUNTRIES_LIST, Gson().toJson(value))?.apply()
        }
}