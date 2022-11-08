package com.example.pixabaygalleryapp.di.repository

import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.network.NetworkResponseResult
import kotlinx.coroutines.flow.Flow

/**
 * @author AliAzazAlam on 11/08/2022.
 */
interface GeneralDataSource {

    /*
    * Get all images from server
    * */
    suspend fun getAllImages(
        page: Int,
        category: String
    ): NetworkResponseResult<ImagesResult>
    /*
    * Get all images from server End
    * */

    /*
    * Get search images
    * */
    suspend fun getSearchImages(
        page: Int,
        search: String
    )
    /*
    * Get search images End
    * */


}