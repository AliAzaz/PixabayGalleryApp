package com.example.pixabaygalleryapp.di.modules

import com.example.pixabaygalleryapp.base.repository.GeneralDataSource
import com.example.pixabaygalleryapp.base.repository.GeneralRepository
import com.example.pixabaygalleryapp.di.auth.AuthApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralRepositoryModule {

    @Singleton
    @Provides
    fun provideGeneralDataSource(authApi: AuthApi): GeneralDataSource {
        return GeneralRepository(authApi)
    }

}