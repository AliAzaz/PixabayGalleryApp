package com.example.pixabaygalleryapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.pixabaygalleryapp.base.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}