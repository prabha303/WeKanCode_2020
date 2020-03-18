package com.prabha.wekan.network.interceptor

import android.content.Context
import android.util.Log
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder: Request.Builder

        val tag = NetworkInterceptor::class.java.simpleName
        val cacheControl = "Cache-Control"

        if (ConnectivityStatus.isConnected(context)) {
            //requestBuilder = original.newBuilder();
            requestBuilder = original.newBuilder()
                .header(cacheControl, "public, max-age=" + 60)
                //.header("add heard", "add h")
                .method(original.method(), original.body())
        } else {
            Log.e(tag, "Inside Non Connectivity status")
            requestBuilder = original.newBuilder().header(cacheControl, "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}
