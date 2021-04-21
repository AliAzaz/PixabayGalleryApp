package com.example.pixabaygalleryapp.base.repository

/**
 * @author AliAzazAlam on 4/20/2021.
 */
enum class ResponseStatus {
    SUCCESS,
    ERROR,
    LOADING
}

data class ResponseStatusCallbacks<out T>(
        val status: ResponseStatus,
        val data: T?,
        val message: String?
) {
    companion object {
        fun <T> success(data: T, message: String? = null): ResponseStatusCallbacks<T> =
                ResponseStatusCallbacks(status = ResponseStatus.SUCCESS, data = data, message = message)

        fun <T> error(data: T?, message: String): ResponseStatusCallbacks<T> =
                ResponseStatusCallbacks(status = ResponseStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResponseStatusCallbacks<T> =
                ResponseStatusCallbacks(status = ResponseStatus.LOADING, data = data, message = null)
    }
}

