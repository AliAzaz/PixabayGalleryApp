package com.example.pixabaygalleryapp.network

import org.apache.commons.lang3.StringUtils
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

data class NetworkResponseError(
    val path: String = StringUtils.EMPTY,
    val message: String,
    val code: Int = -1
)

sealed class NetworkResponseResult<out T> {
    data class Success<out T>(val data: T) : NetworkResponseResult<T>()
    data class Failure(val errorResponse: NetworkResponseError) : NetworkResponseResult<Nothing>()
    object NetworkError : NetworkResponseResult<Nothing>()
    object EmptyResponse : NetworkResponseResult<Nothing>()

}

interface BaseRemoteDataSource {
    suspend fun <T> getResults(call: suspend () -> Response<T>): NetworkResponseResult<T>
}

abstract class DefaultBaseRemoteDataSource() : BaseRemoteDataSource {
    @set:Inject
    internal var errorIErrorFactory: IErrorFactory? = null
    override suspend fun <T> getResults(call: suspend () -> Response<T>): NetworkResponseResult<T> {
        return try {
            val response = call.invoke()
            return if (response.isSuccessful && response.body() != null) {
                NetworkResponseResult.Success(response.body()!!)
            } else {
                NetworkResponseResult.EmptyResponse
            }
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                NetworkResponseResult.NetworkError
            } else {
                NetworkResponseResult.Failure(
                    errorIErrorFactory?.getError(throwable)?.getErrorResponse(throwable)!!
                )
            }
        }
    }
}