package com.huixing.financial.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
//        val request = originalRequest.newBuilder().url(originalRequest.url)
//            .header("token",BuildConfig.ACCESS_TOKEN).build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}
