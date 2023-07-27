package com.example.pixabaygalleryapp.di.modules

import com.example.pixabaygalleryapp.di.auth.AuthApi
import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.di.repository.GeneralRepository
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
    fun provideGeneralDataSource(authApi: AuthApi): GeneralDataSource {
        return GeneralRepository(authApi)
    }

}