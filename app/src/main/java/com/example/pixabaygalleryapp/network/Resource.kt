package com.example.pixabaygalleryapp.network

sealed interface Resource<out T> {
    data class Success<T>(val data: T? = null) : Resource<T>
    object Loading : Resource<Nothing>
    data class Error(val viewError: ViewError) : Resource<Nothing>

    /*override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$viewError]"
            Loading -> "Loading"
        }
    }*/
}