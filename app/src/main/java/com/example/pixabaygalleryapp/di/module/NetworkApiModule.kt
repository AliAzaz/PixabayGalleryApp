package com.example.pixabaygalleryapp.di.module

import com.example.pixabaygalleryapp.BuildConfig
import com.example.pixabaygalleryapp.network.BackendApi
import com.example.pixabaygalleryapp.network.DefaultErrorFactory
import com.example.pixabaygalleryapp.network.IErrorFactory
import com.example.pixabaygalleryapp.utils.CONSTANTS
import com.example.pixabaygalleryapp.utils.StringResourceManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * @author AliAzazAlam on 11/08/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Singleton
    @Provides
    fun buildBackendApi(retrofit: Retrofit): BackendApi {
        return retrofit.create(BackendApi::class.java)
    }

    @Singleton
    @Provides
    fun buildRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun buildOkHttpClient(chainKeyInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder().also { item ->
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            item.addInterceptor(log)
            item.retryOnConnectionFailure(true)
        }
            .addNetworkInterceptor(chainKeyInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun getChainKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.request().let {
                val urlBuilder = it.url.newBuilder()
                    .addQueryParameter(CONSTANTS.KEY, BuildConfig.API_KEY)
                    .build()
                chain.proceed(it.newBuilder().url(urlBuilder).build())
            }
        }
    }

    @Provides
    @Singleton
    fun providesErrorFactory(stringResourceManager: StringResourceManager): IErrorFactory {
        return DefaultErrorFactory(stringResourceManager)
    }

}