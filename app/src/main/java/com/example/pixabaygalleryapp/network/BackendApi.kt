package com.example.pixabaygalleryapp.network

import com.example.pixabaygalleryapp.model.ImagesResult
import com.example.pixabaygalleryapp.utils.CONSTANTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author AliAzazAlam on 11/08/2022.
 */
interface BackendApi {

    @GET(ApiRoutes.GET_IMAGES_DATA)
    suspend fun getImagesData(
        @Query(CONSTANTS.PAGE) page: Int = 1,
        @Query(CONSTANTS.CATEGORY) category: String = "latest"
    ): Response<ImagesResult>

    @GET(ApiRoutes.GET_IMAGES_DATA)
    suspend fun getSearchImageData(
        @Query(CONSTANTS.PAGE) page: Int = 1,
        @Query(CONSTANTS.SEARCH) search: String
    ): Response<ImagesResult>

}