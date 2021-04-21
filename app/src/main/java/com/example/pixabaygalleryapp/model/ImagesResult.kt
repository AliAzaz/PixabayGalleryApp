package com.example.pixabaygalleryapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImagesResult(

    @SerializedName("hits")
    @Expose val imagesInfo: List<ImagesInfo>,

    @Expose val total: Int,
    @Expose val totalHits: Int
)