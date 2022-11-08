package com.example.pixabaygalleryapp.di.auth

import com.example.pixabaygalleryapp.MainCoroutinesRule
import com.example.pixabaygalleryapp.di.module.NetworkApiModuleTest
import com.example.pixabaygalleryapp.network.BackendApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

/**
 * @author AliAzazAlam on 4/21/2021.
 */
class BackendApiTest : NetworkApiModuleTest<BackendApi>() {

    private lateinit var apiService: BackendApi

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        apiService = createService(BackendApi::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test getImageData() returns list of Images`() = runBlocking {
        // Given
        enqueueResponse("/image_response.json")

        // Invoke
        val response = apiService.getImagesData()
        val responseBody = requireNotNull(response.imagesInfo)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(2416718))
        MatcherAssert.assertThat(
            responseBody[0].pageURL,
            CoreMatchers.`is`("https://pixabay.com/photos/baby-hands-fingers-infant-child-2416718/")
        )
        MatcherAssert.assertThat(responseBody[0].type, CoreMatchers.`is`("photo"))
        MatcherAssert.assertThat(responseBody[0].tags, CoreMatchers.`is`("baby, hands, fingers"))
        MatcherAssert.assertThat(
            responseBody[0].previewURL,
            CoreMatchers.`is`("https://cdn.pixabay.com/photo/2017/06/18/18/39/baby-2416718_150.jpg")
        )
        MatcherAssert.assertThat(responseBody[0].previewWidth, CoreMatchers.`is`(150))
        MatcherAssert.assertThat(responseBody[0].previewHeight, CoreMatchers.`is`(101))
        MatcherAssert.assertThat(
            responseBody[0].webformatURL,
            CoreMatchers.`is`("https://pixabay.com/get/gcdb03abd54a9acae8122994d12b830efda50211fa47bfb05acc1f0108f02c5c1a54cc33ef778082ec2b20fa19c42b961ea24a37d86cfa21e2304c943fe4ff845_640.jpg")
        )
        MatcherAssert.assertThat(
            responseBody[0].userImageURL,
            CoreMatchers.`is`("https://cdn.pixabay.com/user/2020/09/07/16-49-31-85_250x250.jpg")
        )
    }
}