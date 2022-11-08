package com.example.pixabaygalleryapp.viewmodel.usecases

import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
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
class ImageUseCase @Inject constructor(
    private val repository: GeneralDataSource,
    private val errorStateMapper: ErrorStateMapper
) {
    suspend operator fun invoke(
        page: Int = 1,
        category: String = "latest"
    ): Flow<Resource<List<ImagesInfo>>> = flow {
        emit(Resource.Loading)
        when (val data = repository.getAllImages(
            page = page,
            category = category
        )) {
            is NetworkResponseResult.Success -> {
                emit(Resource.Success(data.data.imagesInfo))
            }
            else -> emit(errorStateMapper.map(data))
        }
    }
}