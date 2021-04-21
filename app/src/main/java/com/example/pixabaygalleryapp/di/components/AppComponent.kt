package com.example.pixabaygalleryapp.di.components

import android.app.Application
import com.example.pixabaygalleryapp.MainApp
import com.example.pixabaygalleryapp.di.modules.FragmentModule
import com.example.pixabaygalleryapp.di.modules.GeneralRepositoryModule
import com.example.pixabaygalleryapp.di.modules.NetworkApiModule
import com.example.pixabaygalleryapp.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkApiModule::class,
        FragmentModule::class,
        GeneralRepositoryModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MainApp)
}