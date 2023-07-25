package com.example.pixabaygalleryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.di.repository.ResponseStatusCallbacks
import com.example.pixabaygalleryapp.model.FetchDataModel
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.usecases.ImageSearchUseCase
import com.example.pixabaygalleryapp.usecases.ImageUseCase
import com.example.pixabaygalleryapp.utils.DefaultStringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.apache.commons.lang.StringUtils
import javax.inject.Inject

/**
 * @author AliAzazAlam on 4/20/2021.
 */
@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    private val imageSearchUseCase: ImageSearchUseCase,
    private val stringResourceManager: DefaultStringResourceManager
) : ViewModel() {

    private val _imagesList = MutableLiveData<ResponseStatusCallbacks<FetchDataModel>>()
    val imagesResponse: LiveData<ResponseStatusCallbacks<FetchDataModel>>
        get() = _imagesList

    private val _selectedImages = MutableLiveData<ResponseStatusCallbacks<ImagesInfo>>()
    val selectedImagesResponse: LiveData<ResponseStatusCallbacks<ImagesInfo>>
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
                imageUseCase.invoke(page = pagination).collect { dataset ->
                    dataset.imagesInfo.let {
                        if (it.isNotEmpty()) {
                            if (pagination == 1) {
                                updatedItems = arrayListOf()
                                updatedItems.addAll(it)
                            } else {
                                updatedItems.addAll(it)
                            }
                            _imagesList.postValue(
                                ResponseStatusCallbacks.success(
                                    data = FetchDataModel(
                                        page = pagination,
                                        imagesInfo = updatedItems
                                    ),
                                    stringResourceManager.getString(R.string.items_received)
                                )
                            )
                        } else
                            _imagesList.value = ResponseStatusCallbacks.error(
                                data = FetchDataModel(
                                    page = pagination,
                                    imagesInfo = null
                                ),
                                if (pagination == 1)
                                    stringResourceManager.getString(R.string.no_images_received)
                                else
                                    stringResourceManager.getString(R.string.more_images_na)
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
                    stringResourceManager.getString(R.string.items_received)
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
                imageSearchUseCase(page = pagination, category = search).collect { dataset ->
                    dataset.imagesInfo.let {
                        if (it.isNotEmpty()) {
                            if (pagination == 1) {
                                updatedItems = arrayListOf()
                                updatedItems.addAll(it)
                            } else {
                                updatedItems.addAll(it)
                            }
                            _imagesList.postValue(
                                ResponseStatusCallbacks.success(
                                    data = FetchDataModel(
                                        page = pagination,
                                        imagesInfo = updatedItems
                                    ),
                                    stringResourceManager.getString(R.string.items_received)
                                )
                            )
                        } else
                            _imagesList.value = ResponseStatusCallbacks.error(
                                data = FetchDataModel(
                                    page = pagination,
                                    imagesInfo = null
                                ),
                                if (pagination == 1)
                                    stringResourceManager.getString(R.string.no_images_received)
                                else
                                    stringResourceManager.getString(R.string.more_images_na)
                            )
                    }

                }
            } catch (e: Exception) {
                _imagesList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

}