package com.example.pixabaygalleryapp.di.module

import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.network.ErrorStateMapper
import com.example.pixabaygalleryapp.viewmodel.usecases.ImageSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeneralUseCaseModule {

    @Provides
    @Singleton
    fun provideImageSearchUseCase(
        generalDataSource: GeneralDataSource,
        errorStateMapper: ErrorStateMapper
    ) = ImageSearchUseCase(generalDataSource, errorStateMapper)

}