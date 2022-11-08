package com.example.pixabaygalleryapp.di.repository

import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.network.NetworkResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author AliAzazAlam on 11/08/2022.
 */
class GeneralRepository @Inject constructor(private val defaultGeneralDataSource: BaseGeneralDataSource) :
    GeneralDataSource {

    var images: Flow<List<ImagesInfo>> = emptyFlow()

    override suspend fun getAllImages(
        page: Int,
        category: String
    ): NetworkResponseResult<ImagesResult> {
        val items = defaultGeneralDataSource.getAllImages(
            page = page,
            category = category
        )

        return items


    }

    override suspend fun getSearchImages(page: Int, search: String) {
        val items = defaultGeneralDataSource.getSearchImages(
            page = page,
            search = search
        )

        when (items) {
            NetworkResponseResult.EmptyResponse -> {

            }
            is NetworkResponseResult.Failure -> {

            }
            NetworkResponseResult.NetworkError -> {

            }
            is NetworkResponseResult.Success -> {
                images = flow { emit(items.data.imagesInfo.toList()) }
            }
        }
    }

}