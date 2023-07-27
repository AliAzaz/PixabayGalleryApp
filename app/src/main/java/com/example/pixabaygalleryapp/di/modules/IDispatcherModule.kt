package com.example.pixabaygalleryapp.di.modules

import com.example.pixabaygalleryapp.utils.DefaultDispatcherProvider
import com.example.pixabaygalleryapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object IDispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}