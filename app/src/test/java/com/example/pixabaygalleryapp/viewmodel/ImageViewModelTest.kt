package com.example.pixabaygalleryapp.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pixabaygalleryapp.MainCoroutinesRule
import com.example.pixabaygalleryapp.MockTestUtil
import com.example.pixabaygalleryapp.di.repository.ResponseStatusCallbacks
import com.example.pixabaygalleryapp.model.FetchDataModel
import com.example.pixabaygalleryapp.usecases.ImageSearchUseCase
import com.example.pixabaygalleryapp.usecases.ImageUseCase
import com.example.pixabaygalleryapp.utils.DefaultStringResourceManager
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks

/**
 * @author AliAzazAlam on 4/21/2021.
 */
@RunWith(JUnit4::class)
class ImageViewModelTest {

    // Subject under test
    private lateinit var viewModel: ImageViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var imageUseCase: ImageUseCase

    @MockK
    lateinit var imageSearchUseCase: ImageSearchUseCase

    @InjectMocks
    lateinit var stringResourceManager: DefaultStringResourceManager

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        // Create a mock for the ApplicationContext
        val mockContext: Context = mockk(relaxed = true)
        // Create the DefaultStringResourceManager with the mock Context
        stringResourceManager = DefaultStringResourceManager(mockContext)
    }

    @Test
    fun `test getAllImagesFromRemoteServer() returns list of images`() = runBlocking {
        // Given
        val imagesListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchDataModel>>>(relaxed = true)

        // When
        coEvery { imageUseCase.invoke(any(), any()) }
            .returns(flowOf(MockTestUtil.createImages()))

        // Invoke
        viewModel = ImageViewModel(imageUseCase, imageSearchUseCase, stringResourceManager)
        viewModel.imagesResponse.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageUseCase.invoke() }

        coVerify {
            imagesListObserver.onChanged(
                match {
                    it.data != null
                }
            )
        }

        verify { imagesListObserver.onChanged(match { it.data != null }) }
        verify { imagesListObserver.onChanged(match { it.data?.imagesInfo?.size == 1 }) }

    }

    @Test
    fun `test getAllImagesFromRemoteServer() returns zero list of images`() = runBlocking {
        // Given
        val imagesListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchDataModel>>>(relaxed = true)

        // When
        coEvery {
            imageUseCase.invoke(
                any(),
                any()
            )
        }.returns(flowOf(MockTestUtil.createZeroImage()))

        // Invoke
        viewModel = ImageViewModel(imageUseCase, imageSearchUseCase, stringResourceManager)
        viewModel.imagesResponse.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageUseCase.invoke() }
        verify { imagesListObserver.onChanged(match { it.data != null }) }
        verify {
            imagesListObserver.onChanged(match {
                it.data?.imagesInfo?.isNullOrEmpty() ?: true
            })
        }

    }

    @After
    fun tearDown() {
    }
}