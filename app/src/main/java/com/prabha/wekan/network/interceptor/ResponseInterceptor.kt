package com.prabha.wekan.network.interceptor

import android.content.Context
import android.util.Base64
import com.prabha.wekan.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit


class ResponseInterceptor(private val context: Context) : Interceptor {
    var mRetrofit:Retrofit? = null
    val TAG = "fetching refresh token"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().method(original.method(), original.body()).build()
        return chain.proceed(request)

    }



    private fun getRetrofitService():Retrofit? {
        if (mRetrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .client(getOkHttpTokenClient().addInterceptor(interceptor).connectTimeout(120, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS).build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return mRetrofit
    }

    private fun getOkHttpTokenClient(): OkHttpClient.Builder {
        return try {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request: Request
                request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(request)
            }
            builder
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

}
