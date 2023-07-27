package com.example.pixabaygalleryapp.utils

import android.content.Context
import com.example.pixabaygalleryapp.R
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.apache.commons.lang.StringUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DefaultStringResourceManagerTest {

    //Subject under test
    private lateinit var defaultStringResourceManager: DefaultStringResourceManager

    @MockK
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        //Initialize subject under test
        defaultStringResourceManager = DefaultStringResourceManager(context)
    }

    @Test
    fun `Test to get string when resource id is available`() {

        //Given
        val appNameResId = 1900067
        val appNameResValue = "PixabayGalleryApp"

        //Mock the behavior
        every {
            context.getString(appNameResId)
        } returns appNameResValue

        //When
        val result = defaultStringResourceManager.getString(appNameResId)

        //Then
        assertEquals(appNameResValue, result)
    }

    @Test
    fun `Test when string value is not appropriate available`() {
        //Given
        val appNameResId = R.string.app_name
        val appNameValue = StringUtils.EMPTY

        //When
        //Test
        every {
            context.getString(appNameResId)
        }.returns(appNameValue)

        //Invoke
        val result = defaultStringResourceManager.getString(appNameResId)

        //Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(StringUtils.EMPTY))

    }

    @Test
    fun `Test when resource id is not valid`() {
        //Given
        val appNameResId = 123
        val appNameValue = StringUtils.EMPTY

        //When
        //Test
        every {
            context.getString(appNameResId)
        }.returns(appNameValue)

        //Invoke
        val result = defaultStringResourceManager.getString(appNameResId)

        //Then
        assertEquals(result, StringUtils.EMPTY)
    }

}