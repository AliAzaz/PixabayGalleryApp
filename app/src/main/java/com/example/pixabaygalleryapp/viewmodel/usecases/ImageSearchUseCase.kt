package com.example.pixabaygalleryapp.viewmodel.usecases

import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.di.repository.GeneralRepository
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.network.ErrorStateMapper
import com.example.pixabaygalleryapp.network.NetworkResponseResult
import com.example.pixabaygalleryapp.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author AliAzazAlam on 11/08/2022.
 */
class ImageSearchUseCase @Inject constructor(
    private val repository: GeneralDataSource,
    private val errorStateMapper: ErrorStateMapper
) {
    suspend operator fun invoke(
        page: Int = 1,
        search: String
    ): Flow<Resource<List<ImagesInfo>>> = flow {
        emit(Resource.Loading)
        when (val result = repository.getSearchImages(
            page = page,
            search = search
        )) {
            is NetworkResponseResult.Success -> emit(Resource.Success(result.data.imagesInfo))
            else -> emit(errorStateMapper.map(result))
        }
    }
}