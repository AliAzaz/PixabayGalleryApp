package com.example.pixabaygalleryapp.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface IDispatcher {
    fun dispatcherIO(): CoroutineDispatcher
    fun dispatcherMain(): CoroutineDispatcher
    fun dispatcherDefault(): CoroutineDispatcher
}

class DispatcherHelper @Inject constructor() : IDispatcher {

    override fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    override fun dispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    override fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

}