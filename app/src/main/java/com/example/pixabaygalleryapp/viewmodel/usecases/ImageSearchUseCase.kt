package com.example.pixabaygalleryapp.viewmodel.usecases

import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.di.repository.GeneralRepository
import javax.inject.Inject

/**
 * @author AliAzazAlam on 11/08/2022.
 */
class ImageSearchUseCase @Inject constructor(private val repository: GeneralRepository) {
    suspend operator fun invoke(
        page: Int = 1,
        category: String
    ) = repository.getSearchImages(
        page = page,
        search = category
    )
}