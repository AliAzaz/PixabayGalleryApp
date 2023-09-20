package com.example.pixabaygalleryapp.utils

/**
 * @author AliAzazAlam on 9/7/2021.
 */

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String?
}