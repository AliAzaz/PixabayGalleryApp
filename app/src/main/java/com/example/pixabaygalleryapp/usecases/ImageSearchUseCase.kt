package com.example.pixabaygalleryapp.usecases

import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import javax.inject.Inject

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class ImageSearchUseCase @Inject constructor(private val repository: GeneralDataSource) {
    suspend operator fun invoke(
        page: Int = 1,
        category: String
    ) = repository.getSearchImages(
        page = page,
        search = category
    )
}