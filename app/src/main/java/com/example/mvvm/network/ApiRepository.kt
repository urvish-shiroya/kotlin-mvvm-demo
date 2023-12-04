package com.example.mvvm.network

import com.example.mvvm.models.Countries
import retrofit2.Callback


object ApiRepository {
    private fun getClient(): ApiInterface {
        return RestClient.getRetrofit().create(ApiInterface::class.java)
    }

    fun getAllCountries(callback: Callback<List<Countries>>): Unit {
        return getClient().getAllCountries().enqueue(callback)
    }
}

