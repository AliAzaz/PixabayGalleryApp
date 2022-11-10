package com.example.pixabaygalleryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.di.repository.GeneralDataSource
import com.example.pixabaygalleryapp.di.repository.ResponseStatusCallbacks
import com.example.pixabaygalleryapp.helper.DispatcherHelper
import com.example.pixabaygalleryapp.viewmodel.usecases.ImageSearchUseCase
import com.example.pixabaygalleryapp.model.FetchDataModel
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.network.Resource
import com.example.pixabaygalleryapp.utils.StringResourceManager
import com.example.pixabaygalleryapp.utils.launchItemInCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

/**
 * @author AliAzazAlam on 11/08/2022.
 */
interface IImageViewModel {
    fun showSuccessSnackBar(): SharedFlow<String>

    fun showErrorSnackBar(): SharedFlow<String>

    fun imagesResult(): LiveData<List<ImagesInfo>>

    fun loadNextPagePhotos(pagination: Int, fetchedImages: List<ImagesInfo>, searchImage: String)

    fun retryConnection(pagination: Int, fetchedImages: List<ImagesInfo>, searchImage: String)

    fun fetchImagesFromRemoteServer(pagination: Int, fetchedImages: List<ImagesInfo> = listOf())

    fun fetchSearchImagesFromRemoteServer(
        pagination: Int,
        fetchedImages: List<ImagesInfo>,
        searchImage: String
    )

    fun selectedImage(): LiveData<ImagesInfo>

    fun setSelectedProduct(singleImage: ImagesInfo)

}


@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: GeneralDataSource,
    private val imageSearchUseCase: ImageSearchUseCase,
    private val stringResourceManager: StringResourceManager,
    private val dispatcher: DispatcherHelper
) : ViewModel(), IImageViewModel {

    private val _selectedImages = MutableLiveData<ImagesInfo>()
    private val _successSnackBarStates = MutableSharedFlow<String>(replay = 0)
    private val _errorSnackBarStates = MutableSharedFlow<String>(replay = 0)
    private val _imagesResult = MutableLiveData<List<ImagesInfo>>()

    init {
        fetchImagesFromRemoteServer(1)
    }

    /*
    * Show snackbar if success would occur
    * */
    override fun showSuccessSnackBar(): SharedFlow<String> {
        return _successSnackBarStates
    }

    /*
    * Show snackbar if error would occur
    * */
    override fun showErrorSnackBar(): SharedFlow<String> {
        return _errorSnackBarStates
    }

    /*
    * Get all images
    * */
    override fun imagesResult(): LiveData<List<ImagesInfo>> {
        return _imagesResult
    }

    /*
    * Observed function for initiate searching
    * */
    override fun fetchImagesFromRemoteServer(pagination: Int, fetchedImages: List<ImagesInfo>) {
        viewModelScope.launch {
            imageRepository.getAllImages(page = pagination).stateIn(
                scope = viewModelScope,
                initialValue = Resource.Loading,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000)
            ).collect {
                when (it) {
                    Resource.Loading ->
                        if (pagination > 1) _successSnackBarStates.launchItemInCoroutine(
                            dispatcher.dispatcherMain(),
                            stringResourceManager.getString(R.string.loading_images)
                        )
                    is Resource.Error ->
                        _errorSnackBarStates.launchItemInCoroutine(
                            dispatcher.dispatcherMain(),
                            it.viewError.message
                        )
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty()) {
                            _successSnackBarStates.launchItemInCoroutine(
                                dispatcher.dispatcherMain(),
                                stringResourceManager.getString(R.string.no_images)
                            )
                        } else {
                            _imagesResult.launchItemInCoroutine(
                                dispatcher.dispatcherMain(),
                                fetchedImages + it.data
                            )
                        }
                    }
                }
            }
        }
    }

    /*
    * Send data to detail page
    * */
    override fun setSelectedProduct(singleImage: ImagesInfo) {
        _selectedImages.launchItemInCoroutine(
            dispatcher.dispatcherMain(),
            singleImage
        )
    }

    /*
    * Get data in detail page
    * */
    override fun selectedImage(): LiveData<ImagesInfo> {
        return _selectedImages
    }

    /*
    * load next page
    * */
    override fun loadNextPagePhotos(
        pagination: Int,
        fetchedImages: List<ImagesInfo>,
        searchImage: String
    ) {
        if (searchImage == StringUtils.EMPTY) {
            fetchImagesFromRemoteServer(pagination, fetchedImages)
        } else {
            fetchSearchImagesFromRemoteServer(pagination, fetchedImages, searchImage)
        }
    }

    /*
    * Retry connection if internet is not available
    * */
    override fun retryConnection(
        pagination: Int,
        fetchedImages: List<ImagesInfo>,
        searchImage: String
    ) {
        if (searchImage == StringUtils.EMPTY) {
            fetchImagesFromRemoteServer(pagination, fetchedImages)
        } else {
            fetchSearchImagesFromRemoteServer(pagination, fetchedImages, searchImage)
        }
    }

    /*
    * Search function for searching photos by name
    * Query to fetch images from server
    * */
    override fun fetchSearchImagesFromRemoteServer(
        pagination: Int,
        fetchedImages: List<ImagesInfo>,
        searchImage: String
    ) {
        viewModelScope.launch {
            imageSearchUseCase.invoke(page = pagination, search = searchImage).stateIn(
                scope = viewModelScope,
                initialValue = Resource.Loading,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000)
            ).collect {
                when (it) {
                    Resource.Loading ->
                        if (pagination > 1) _successSnackBarStates.launchItemInCoroutine(
                            dispatcher.dispatcherMain(),
                            stringResourceManager.getString(R.string.loading_images)
                        )
                    is Resource.Error ->
                        _errorSnackBarStates.launchItemInCoroutine(
                            dispatcher.dispatcherMain(),
                            it.viewError.message
                        )
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty()) {
                            _successSnackBarStates.launchItemInCoroutine(
                                dispatcher.dispatcherMain(),
                                stringResourceManager.getString(R.string.no_images)
                            )
                        } else {
                            _imagesResult.launchItemInCoroutine(
                                dispatcher.dispatcherMain(),
                                fetchedImages + it.data
                            )
                        }
                    }
                }
            }
        }
    }

}