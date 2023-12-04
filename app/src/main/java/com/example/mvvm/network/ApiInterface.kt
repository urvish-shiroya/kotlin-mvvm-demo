package com.example.mvvm.network

import com.example.mvvm.models.Countries
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("v3.1/all")
    fun getAllCountries(): Call<List<Countries>>

}