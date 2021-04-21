package com.example.pixabaygalleryapp.di.auth

import com.example.pixabaygalleryapp.model.ImagesResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author AliAzazAlam on 4/20/2021.
 */
interface AuthApi {

    @GET(ApiRoutes.GET_IMAGES_DATA)
    suspend fun getImagesData(
        @Query("page") page: Int = 1,
        @Query("per_page") perPagePhotos: Int = 26,
        @Query("category") category: String = "latest"
    ): ImagesResult

    @GET(ApiRoutes.GET_IMAGES_DATA)
    suspend fun getSearchImageData(
        @Query("page") page: Int = 1,
        @Query("per_page") perPagePhotos: Int = 26,
        @Query("q") category: String
    ): ImagesResult

}