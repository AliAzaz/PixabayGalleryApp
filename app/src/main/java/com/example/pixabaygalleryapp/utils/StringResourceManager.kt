package com.example.pixabaygalleryapp.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.apache.commons.lang.StringUtils
import javax.inject.Inject

interface StringResourceManager {
    fun getString(resId: Int): String
    fun getString(resId: Int, param: String): String
    fun getString(resId: Int, param: String, param2: String): String
}

class DefaultStringResourceManager @Inject constructor(@ApplicationContext private val context: Context?) :
    StringResourceManager {
    override fun getString(resId: Int): String {
        return context?.getString(resId) ?: StringUtils.EMPTY
    }

    override fun getString(resId: Int, param: String): String {
        return context?.getString(resId, param) ?: StringUtils.EMPTY
    }

    override fun getString(resId: Int, param: String, param2: String): String {
        return context?.getString(resId, param, param2) ?: StringUtils.EMPTY
    }

}