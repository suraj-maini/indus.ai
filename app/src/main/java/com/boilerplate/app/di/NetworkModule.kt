package com.boilerplate.app.di

import android.content.Context
import android.os.Build
import android.util.Log
import com.boilerplate.app.BuildConfig
import com.boilerplate.app.R
import com.boilerplate.app.common.Constants.HEADER_CACHE_CONTROL
import com.boilerplate.app.common.Constants.HEADER_PRAGMA
import com.boilerplate.app.data.service.ApiService
import com.boilerplate.app.utils.NetworkUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://hassanrsiddiqi.com/"

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory =
        MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory =
        KotlinJsonAdapterFactory()

    @Singleton
    @Provides
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi =
        Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()

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
            .addInterceptor(provideOfflineCacheInterceptor(context))
            .addInterceptor(UserAgentInterceptor(context.getString(R.string.app_name) + "/" + BuildConfig.VERSION_NAME + " (" + BuildConfig.APPLICATION_ID + "; build:" + BuildConfig.VERSION_CODE + "; android " + Build.VERSION.RELEASE + ") " + OkHttp.VERSION))
            .addNetworkInterceptor(provideCacheInterceptor(context))
            .cache(provideCache(context))

        if (BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        return httpClient.build()
    }



    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024) // 10 MB
        } catch (e: Exception) {
            Log.e("NetworkModule","Cache: Could not create Cache!")
        }
        return cache
    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.isInternetAvailable(context)) {
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }



    @Provides
    @Singleton
    fun provideCacheInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl: CacheControl = if (NetworkUtils.isInternetAvailable(context)) {
                CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()
            } else {
                CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()
            }
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }


    class UserAgentInterceptor(private val userAgent: String): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain
                .request().newBuilder()
            builder.addHeader("User-Agent", userAgent)
            return chain.proceed(builder.build())
        }

    }

}