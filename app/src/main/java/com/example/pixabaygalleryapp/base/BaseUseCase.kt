package com.example.pixabaygalleryapp.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, out R> {
    operator fun invoke(params: P? = null): Flow<R>
    fun getDispatcher(): CoroutineDispatcher
}