package com.example.pixabaygalleryapp.di.repository

import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.network.ErrorStateMapper
import com.example.pixabaygalleryapp.network.NetworkResponseResult
import com.example.pixabaygalleryapp.network.Resource
import kotlinx.coroutines.flow.*
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

/**
 * @author AliAzazAlam on 11/08/2022.
 */
interface GeneralDataSource {
    suspend fun getAllImages(
        page: Int,
        category: String = StringUtils.EMPTY
    ): Flow<Resource<List<ImagesInfo>>>

    suspend fun getSearchImages(page: Int, search: String): NetworkResponseResult<ImagesResult>
}

class GeneralRepository @Inject constructor(
    private val defaultGeneralDataSource: BaseGeneralDataSource,
    private val errorStateMapper: ErrorStateMapper
) : GeneralDataSource {

    override suspend fun getAllImages(
        page: Int,
        category: String
    ): Flow<Resource<List<ImagesInfo>>> {
        return flow {
            emit(Resource.Loading)
            when (val result = defaultGeneralDataSource.getAllImages(
                page = page,
                category = category
            )) {
                is NetworkResponseResult.Success -> emit(Resource.Success(result.data.imagesInfo))
                else -> emit(errorStateMapper.map(result))
            }
        }
    }

    override suspend fun getSearchImages(
        page: Int,
        search: String
    ): NetworkResponseResult<ImagesResult> {
        return defaultGeneralDataSource.getSearchImages(
            page = page,
            search = search
        )
    }

}