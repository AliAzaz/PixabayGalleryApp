package com.example.pixabaygalleryapp.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaygalleryapp.base.repository.ResponseStatusCallbacks
import com.example.pixabaygalleryapp.base.viewmodel.usecases.ImageSearchUseCase
import com.example.pixabaygalleryapp.base.viewmodel.usecases.ImageUseCase
import com.example.pixabaygalleryapp.model.FetchDataModel
import com.example.pixabaygalleryapp.model.ImagesInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class ImageViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    private val imageSearchUseCase: ImageSearchUseCase
) : ViewModel() {

    private val _imagesList: MutableLiveData<ResponseStatusCallbacks<FetchDataModel>> =
        MutableLiveData()

    val imagesResponse: MutableLiveData<ResponseStatusCallbacks<FetchDataModel>>
        get() = _imagesList

    private val _selectedImages: MutableLiveData<ResponseStatusCallbacks<ImagesInfo>> =
        MutableLiveData()

    val selectedImagesResponse: MutableLiveData<ResponseStatusCallbacks<ImagesInfo>>
        get() = _selectedImages

    private var pagination = 1
    private var searchImage = StringUtils.EMPTY
    private var updatedItems = arrayListOf<ImagesInfo>()

    init {
        fetchImagesFromRemoteServer(pagination)
    }

    /*
    * Observed function for initiate searching
    * */
    fun fetchImagesFromRemoteServer(pagination: Int) {
        _imagesList.value = ResponseStatusCallbacks.loading(
            data = FetchDataModel(
                page = pagination,
                imagesInfo = null
            )
        )
        viewModelScope.launch {
            try {
                delay(500)
                imageUseCase(page = pagination).collect { dataset ->
                    dataset.imagesInfo.let {
                        if (it.isNotEmpty()) {
                            if (pagination == 1) {
                                updatedItems = arrayListOf()
                                updatedItems.addAll(it)
                            } else {
                                updatedItems.addAll(it)
                            }
                            _imagesList.value = ResponseStatusCallbacks.success(
                                data = FetchDataModel(page = pagination, imagesInfo = updatedItems),
                                "Products received"
                            )
                        } else
                            _imagesList.value = ResponseStatusCallbacks.error(
                                data = FetchDataModel(
                                    page = pagination,
                                    imagesInfo = null
                                ),
                                if (pagination == 1) "Sorry no images received" else "Sorry no more images available"
                            )
                    }

                }
            } catch (e: Exception) {
                _imagesList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

    /*
    * Send data to detail page
    * */
    fun setSelectedProduct(singleImages: ImagesInfo) {
        _selectedImages.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                _selectedImages.value = ResponseStatusCallbacks.success(
                    data = singleImages,
                    "Image received"
                )
            } catch (e: Exception) {
                _imagesList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

    /*
    * load next page
    * */
    fun loadNextPagePhotos() {
        pagination++
        if (searchImage == StringUtils.EMPTY) {
            fetchImagesFromRemoteServer(pagination)
        } else {
            fetchSearchImagesFromRemoteServer(pagination, searchImage)
        }

    }

    /*
    * Retry connection if internet is not available
    * */
    fun retryConnection() {
        if (searchImage == StringUtils.EMPTY) {
            fetchImagesFromRemoteServer(pagination)
        } else {
            fetchSearchImagesFromRemoteServer(pagination, searchImage)
        }
    }

    /*
    * Search function for searching photos by name
    * */
    fun searchImagesFromRemote(search: String) {
        pagination = 1
        searchImage = search
        fetchSearchImagesFromRemoteServer(pagination, search)
    }


    /*
    * Query to fetch images from server
    * */
    private fun fetchSearchImagesFromRemoteServer(pagination: Int, search: String) {
        _imagesList.value = ResponseStatusCallbacks.loading(
            data = FetchDataModel(
                page = pagination,
                imagesInfo = null
            )
        )
        viewModelScope.launch {
            try {
                delay(500)
                imageSearchUseCase(page = pagination, category = search).collect { dataset ->
                    dataset.imagesInfo.let {
                        if (it.isNotEmpty()) {
                            if (pagination == 1) {
                                updatedItems = arrayListOf()
                                updatedItems.addAll(it)
                            } else {
                                updatedItems.addAll(it)
                            }
                            _imagesList.value = ResponseStatusCallbacks.success(
                                data = FetchDataModel(page = pagination, imagesInfo = updatedItems),
                                "Products received"
                            )
                        } else
                            _imagesList.value = ResponseStatusCallbacks.error(
                                data = FetchDataModel(
                                    page = pagination,
                                    imagesInfo = null
                                ),
                                if (pagination == 1) "Sorry no images received" else "Sorry no more images available"
                            )
                    }

                }
            } catch (e: Exception) {
                _imagesList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

}