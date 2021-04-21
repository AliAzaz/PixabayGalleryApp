package com.example.pixabaygalleryapp.model

import com.google.gson.annotations.Expose

data class ImagesInfo(
    @Expose val comments: Int,
    @Expose val downloads: Int,
    @Expose val favorites: Int,
    @Expose val id: Int,
    @Expose val imageHeight: Int,
    @Expose val imageSize: Int,
    @Expose val imageWidth: Int,
    @Expose val largeImageURL: String,
    @Expose val likes: Int,
    @Expose val pageURL: String,
    @Expose val previewHeight: Int,
    @Expose val previewURL: String,
    @Expose val previewWidth: Int,
    @Expose val tags: String,
    @Expose val type: String,
    @Expose val user: String,
    @Expose val userImageURL: String,
    @Expose val user_id: Int,
    @Expose val views: Int,
    @Expose val webformatHeight: Int,
    @Expose val webformatURL: String,
    @Expose val webformatWidth: Int
)