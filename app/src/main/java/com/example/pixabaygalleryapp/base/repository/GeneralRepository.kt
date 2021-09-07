package com.example.pixabaygalleryapp.base.repository

import com.example.pixabaygalleryapp.di.auth.AuthApi
import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.utils.Keys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class GeneralRepository @Inject constructor(private val apiService: AuthApi) : GeneralDataSource {

    override suspend fun getAllImages(
        page: Int,
        category: String
    ): Flow<ImagesResult> {
        return flow {
            emit(
                apiService.getImagesData(
                    key = Keys.apiKey(),
                    page = page,
                    category = category
                )
            )
        }
    }

    override suspend fun getSearchImages(page: Int, search: String): Flow<ImagesResult> {
        return flow {
            emit(
                apiService.getSearchImageData(
                    key = Keys.apiKey(),
                    page = page,
                    search = search
                )
            )
        }
    }

}