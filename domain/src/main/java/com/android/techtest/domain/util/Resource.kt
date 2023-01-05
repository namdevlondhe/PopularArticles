package com.android.techtest.domain.util

data class Resource<out T>(var status: Status, val data: T?, var message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}

/*sealed class Resource<T>{

    data class Success<T>(val status: Status = Status.SUCCESS, val data:T): Resource<T>()

    data class Error< T> (val status: Status = Status.ERROR, val message:String): Resource<T>()

    data class Loading(val status: Status = Status.LOADING):Resource<Nothing>()

}*/
