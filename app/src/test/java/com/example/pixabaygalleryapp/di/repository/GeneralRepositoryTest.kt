package com.example.pixabaygalleryapp.di.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pixabaygalleryapp.MainCoroutinesRule
import com.example.pixabaygalleryapp.MockTestUtil
import com.example.pixabaygalleryapp.network.BackendApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author AliAzazAlam on 4/21/2021.
 */
@RunWith(JUnit4::class)
class GeneralRepositoryTest {

    // Suject under test
    private lateinit var repository: GeneralRepository

    @MockK
    private lateinit var backendApi: BackendApi

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getAllImages returns list of images`() = runBlocking {
        // Given
        repository = GeneralRepository(backendApi)

        // When
        coEvery { backendApi.getImagesData(any(), any()) }.returns(MockTestUtil.createImages())

        // Invoke
        val imagesFlow = repository.getAllImages(1, "")

        // Then
        MatcherAssert.assertThat(imagesFlow, CoreMatchers.notNullValue())

        val images = imagesFlow.first()
        MatcherAssert.assertThat(images, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.imagesInfo, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.imagesInfo.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(images.imagesInfo[0].id, CoreMatchers.`is`(2416718))
    }

    @Test
    fun `test getAllImages returns zero list of images`() = runBlocking {
        // Given
        repository = GeneralRepository(backendApi)
        val imagesList = MockTestUtil.createZeroImage()

        // When
        coEvery { backendApi.getImagesData(any(), any()) }.returns(imagesList)

        // Invoke
        val imagesFlow = repository.getAllImages(1, "")

        // Then
        MatcherAssert.assertThat(imagesFlow, CoreMatchers.notNullValue())

        val images = imagesFlow.first()
        MatcherAssert.assertThat(images, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.imagesInfo, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            images.imagesInfo.size,
            CoreMatchers.`is`(imagesList.imagesInfo.size)
        )
    }

    @Test
    fun `test searchImages returns list of images`() = runBlocking {
        //Given
        repository = GeneralRepository(backendApi)
        val searchImage = MockTestUtil.createImages()

        //When
        coEvery {
            backendApi.getSearchImageData(any(), any())
        }.returns(searchImage)

        //Invoke
        val imagesFlow = repository.getSearchImages(1, "")

        //Then
        MatcherAssert.assertThat(imagesFlow, CoreMatchers.notNullValue())

        val images = imagesFlow.first()
        MatcherAssert.assertThat(images, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.imagesInfo, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            images.imagesInfo.size,
            CoreMatchers.`is`(searchImage.imagesInfo.size)
        )

    }

    @After
    fun tearDown() {

    }
}