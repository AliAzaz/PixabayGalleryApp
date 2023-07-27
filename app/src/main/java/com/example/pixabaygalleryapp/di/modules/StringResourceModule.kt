package com.example.pixabaygalleryapp.di.modules

import android.content.Context
import com.example.pixabaygalleryapp.utils.DefaultStringResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StringResourceModule {

    @Provides
    @Singleton
    fun provideStringResource(@ApplicationContext context: Context): DefaultStringResourceManager {
        return DefaultStringResourceManager(context)
    }

}