package com.example.pixabaygalleryapp.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


fun <T> MutableLiveData<T>.launchItemInCoroutine(iDispatcher: CoroutineDispatcher, data: T) {
    CoroutineScope(iDispatcher).launch {
        postValue(data)
    }
}

fun <T> MutableSharedFlow<T>.launchItemInCoroutine(iDispatcher: CoroutineDispatcher, data: T) {
    CoroutineScope(iDispatcher).launch {
        emit(data)
    }
}

fun Fragment.launchItemInScope(item: suspend () -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            item.invoke()
        }
    }
}