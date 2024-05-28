package com.boilerplate.app.di

import android.content.Context
import com.boilerplate.app.data.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "todo.com"

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory =
        MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        return httpClient.build()
    }

}