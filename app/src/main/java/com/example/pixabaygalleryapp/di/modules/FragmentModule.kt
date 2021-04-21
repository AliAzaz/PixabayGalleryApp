package com.example.pixabaygalleryapp.di.modules

import com.example.pixabaygalleryapp.ui.fragment.ImageDetailFragment
import com.example.pixabaygalleryapp.ui.fragment.ImageListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ali Azaz
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindProductListFragment(): ImageListFragment

    @ContributesAndroidInjector
    abstract fun bindProductDetailFragment(): ImageDetailFragment
}