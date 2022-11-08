package com.example.pixabaygalleryapp.network

import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.utils.StringResourceManager
import retrofit2.HttpException
import java.io.EOFException
import java.net.UnknownHostException
import javax.inject.Inject

interface ErrorResponse {
    fun getErrorResponse(throwable: Any? = null): NetworkResponseError
}

interface IErrorFactory {
    fun getError(type: Throwable): ErrorResponse
}

class DefaultErrorFactory @Inject constructor(private val stringResourceManager: StringResourceManager) :
    IErrorFactory {
    override fun getError(type: Throwable): ErrorResponse {
        return when (type) {
            is UnknownHostException -> InternetError(stringResourceManager)
            is EOFException -> NoResultFoundError(stringResourceManager)
            is HttpException -> HttpError(stringResourceManager)
            else -> UnExpectedError(stringResourceManager)
        }
    }

}

private class InternetError @Inject constructor(
    private val stringResourceManager: StringResourceManager
) : ErrorResponse {
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(
            message = stringResourceManager.getString(R.string.INTERNET_ERROR)
        )
    }
}

private class NoResultFoundError @Inject constructor(
    private val stringResourceManager: StringResourceManager
) : ErrorResponse {
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(
            message = stringResourceManager.getString(R.string.NO_RESULT_FOUND)
        )
    }

}

private class UnExpectedError @Inject constructor
    (
    private val stringResourceManager: StringResourceManager
) : ErrorResponse {
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(
            message = stringResourceManager.getString(R.string.UNEXPECTED_ERROR)
        )
    }

}

private class HttpError @Inject constructor(
    private val stringResourceManager: StringResourceManager
) : ErrorResponse {
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        if (throwable is HttpException) {
            throwable.response()?.let { response ->
                return NetworkResponseError(
                    code = response.code(),
                    path = stringResourceManager.getString(R.string.something_went_wrong),
                    message = response.errorBody()?.string().toString()
                )
            }
        }
        return UnExpectedError(stringResourceManager).getErrorResponse()
    }

}