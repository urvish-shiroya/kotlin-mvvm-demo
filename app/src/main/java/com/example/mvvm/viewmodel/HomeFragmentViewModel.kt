package com.example.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.mvvm.R
import com.example.mvvm.base.BaseViewModel
import com.example.mvvm.models.Countries
import com.example.mvvm.network.ApiRepository
import com.example.mvvm.network.NetworkResponse
import com.example.mvvm.utils.Store
import com.example.mvvm.utils.extentions.generateExpiredTime
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel(application: Application) : BaseViewModel(application) {

    private var _allCountriesMutableStateFlow =
        MutableStateFlow<NetworkResponse<List<Countries>>>(NetworkResponse.Default())
    internal var allCountriesAsSharedFlow = _allCountriesMutableStateFlow.asSharedFlow()
    private var countriesJob: Job? = null

    fun getListFromCache() {
        countriesJob?.cancel()
        countriesJob = viewModelScope.launch {
            _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Loading())

            _allCountriesMutableStateFlow.tryEmit(
                NetworkResponse.Success(
                    Store.countriesList ?: listOf()
                )
            )
        }
    }

    fun getAllCountries() {
        countriesJob?.cancel()
        countriesJob = viewModelScope.launch {
            _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Loading())
            try {
                if (isInternetConnected()) {
                    ApiRepository.getAllCountries(object : Callback<List<Countries>> {
                        override fun onResponse(call: retrofit2.Call<List<Countries>>, response: Response<List<Countries>>) {
                            if (response.isSuccessful) {
                                Store.expiredTime = generateExpiredTime(10)
                                Store.countriesList = response.body()
                                _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Success(response.body()!!))
                            } else {
                                _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Failure(response.message()))
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<List<Countries>>, t: Throwable) {
                            _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Failure(t.message.toString()))
                        }
                    })
                } else {
                    _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Failure(mContext.getString(R.string.internet_disconnected)))
                }
            } catch (e: Exception) {
                _allCountriesMutableStateFlow.tryEmit(NetworkResponse.Failure(e.message.toString()))
            }
        }
    }

}