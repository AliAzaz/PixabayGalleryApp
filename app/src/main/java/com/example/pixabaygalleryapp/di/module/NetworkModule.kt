package com.example.pixabaygalleryapp.di.module

import android.content.Context
import com.example.pixabaygalleryapp.network.DefaultErrorStateMapper
import com.example.pixabaygalleryapp.network.ErrorStateMapper
import com.example.pixabaygalleryapp.utils.DefaultStringResourceManager
import com.example.pixabaygalleryapp.utils.StringResourceManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideStringResManager(@ApplicationContext context: Context): StringResourceManager =
        DefaultStringResourceManager(context)


    @Provides
    @Singleton
    fun provideErrorStateMapper(
        stringResourceManager: StringResourceManager,
        gson: Gson
    ): ErrorStateMapper =
        DefaultErrorStateMapper(stringResourceManager, gson)

}