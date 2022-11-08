package com.example.pixabaygalleryapp.di.repository

import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.network.BackendApi
import com.example.pixabaygalleryapp.network.DefaultBaseRemoteDataSource
import com.example.pixabaygalleryapp.network.NetworkResponseResult
import javax.inject.Inject


interface BaseGeneralDataSource {
    suspend fun getAllImages(page: Int, category: String): NetworkResponseResult<ImagesResult>
    suspend fun getSearchImages(page: Int, search: String): NetworkResponseResult<ImagesResult>
}

class DefaultGeneralDataSource @Inject constructor(val backendApi: BackendApi) :
    BaseGeneralDataSource, DefaultBaseRemoteDataSource() {

    override suspend fun getAllImages(
        page: Int,
        category: String
    ): NetworkResponseResult<ImagesResult> {
        return getResults {
            backendApi.getImagesData(page, category)
        }
    }

    override suspend fun getSearchImages(
        page: Int,
        search: String
    ): NetworkResponseResult<ImagesResult> {
        return getResults {
            backendApi.getSearchImageData(page, search)
        }
    }


}