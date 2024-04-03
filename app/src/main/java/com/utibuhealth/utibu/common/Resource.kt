package com.utibuhealth.utibu.common

sealed class Resource<out T> {
    abstract val data: T?

    data class Success<out T>(override val data: T) : Resource<T>()
    data class Error<out T>(val message: String, override val data: T? = null) : Resource<T>()
    object Loading : Resource<Nothing>() {
        override val data: Nothing? = null
    }
}



