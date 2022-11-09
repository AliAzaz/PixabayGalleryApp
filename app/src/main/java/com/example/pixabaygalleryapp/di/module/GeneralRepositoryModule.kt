package com.example.pixabaygalleryapp.di.module

import com.example.pixabaygalleryapp.di.repository.BaseGeneralDataSource
import com.example.pixabaygalleryapp.di.repository.DefaultGeneralDataSource
import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.di.repository.GeneralRepository
import com.example.pixabaygalleryapp.network.BackendApi
import com.example.pixabaygalleryapp.network.ErrorStateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeneralRepositoryModule {

    @Singleton
    @Provides
    fun provideGeneralDataSource(
        baseGeneralDataSource: BaseGeneralDataSource,
        errorStateMapper: ErrorStateMapper
    ): GeneralDataSource {
        return GeneralRepository(baseGeneralDataSource, errorStateMapper)
    }

    @Singleton
    @Provides
    fun provideDefaultGeneralDataSource(backendApi: BackendApi): BaseGeneralDataSource {
        return DefaultGeneralDataSource(backendApi)
    }

}