package com.example.mvvm.network

sealed class NetworkResponse<T>(
    val data: T? = null,
    val error: String? = null,
) {
    class Default<T>() : NetworkResponse<T>()
    class Loading<T>() : NetworkResponse<T>()
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    class Failure<T>(error: String) : NetworkResponse<T>(error = error)
}