package com.example.pixabaygalleryapp.di.modules

import com.example.pixabaygalleryapp.BuildConfig
import com.example.pixabaygalleryapp.di.auth.AuthApi
import com.example.pixabaygalleryapp.utils.CONSTANTS
import com.example.pixabaygalleryapp.utils.CONSTANTS.BASE_URL
import com.example.pixabaygalleryapp.utils.Keys
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
 * @author AliAzazAlam on 4/20/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Singleton
    @Provides
    fun buildBackendApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun buildRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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
                    .addQueryParameter(
                        CONSTANTS.KEY,
                        Keys.apiKey().ifEmpty { BuildConfig.PIXABAY_KEY }
                    )
                    .build()
                chain.proceed(it.newBuilder().url(urlBuilder).build())
            }
        }
    }

}