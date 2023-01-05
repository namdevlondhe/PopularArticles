package com.android.techtest.domain.util

sealed class Resource<T> {

    data class Success<T>(val status: Status = Status.SUCCESS, val data: T) : Resource<T>()

    data class Error<T>(val status: Status = Status.ERROR, val message: String) : Resource<T>()

    data class Loading<T>(val status: Status = Status.LOADING) : Resource<T>()

}
