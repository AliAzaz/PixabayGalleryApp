package com.example.pixabaygalleryapp.di.auth

import com.example.pixabaygalleryapp.utils.CONSTANTS

/**
 * @author AliAzazAlam on 4/20/2021.
 */
object ApiRoutes {
    const val GET_IMAGES_DATA = "api/?key=${CONSTANTS.API_KEY}&safesearch=true&image_type=photo&per_page=26"
}