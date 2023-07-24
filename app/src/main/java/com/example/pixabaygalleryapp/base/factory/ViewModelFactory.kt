package com.example.pixabaygalleryapp.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pixabaygalleryapp.base.repository.GeneralRepository
import com.example.pixabaygalleryapp.base.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.base.viewmodel.usecases.ImageSearchUseCase
import com.example.pixabaygalleryapp.base.viewmodel.usecases.ImageUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 4/20/2021.
 */
@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val repository: GeneralRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ImageViewModel::class.java) -> ImageViewModel(
                ImageUseCase(repository),
                ImageSearchUseCase(repository)
            ) as T
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    }

}