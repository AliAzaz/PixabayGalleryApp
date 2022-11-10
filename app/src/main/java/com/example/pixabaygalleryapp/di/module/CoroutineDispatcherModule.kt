package com.example.pixabaygalleryapp.di.module

import com.example.pixabaygalleryapp.helper.DispatcherHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher() = DispatcherHelper()

}