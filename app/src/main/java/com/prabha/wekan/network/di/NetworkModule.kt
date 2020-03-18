package com.lynkdriver.lynk.network.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prabha.wekan.BuildConfig
import com.prabha.wekan.network.interceptor.NetworkInterceptor
import com.prabha.wekan.network.interceptor.ResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    @Provides
    @Singleton
    internal fun provideNetworkInterceptor(application: Application): NetworkInterceptor {
        return NetworkInterceptor(application.applicationContext)
    }

    @Provides
    @Singleton
    internal fun provideResponseInterceptor(application: Application): ResponseInterceptor {
        return ResponseInterceptor(application)
    }


    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache, networkInterceptor: NetworkInterceptor, responseInterceptor: ResponseInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(networkInterceptor)
        httpClient.addInterceptor(responseInterceptor)
//        httpClient.addInterceptor(mockInterceptor)
        httpClient.addInterceptor(logging)
        httpClient.retryOnConnectionFailure(false)
        httpClient.connectTimeout(180, TimeUnit.SECONDS)
        httpClient.readTimeout(180, TimeUnit.SECONDS)
        httpClient.writeTimeout(180, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .build()
    }

}