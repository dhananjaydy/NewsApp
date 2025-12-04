package com.example.newsapp.data.remote

import com.example.newsapp.di.qualifiers.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class APIKeyInterceptor @Inject constructor(
    @ApiKey private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val newRequest = originalRequest
                .newBuilder()
                .url(newUrl)
                .build()

        return chain.proceed(newRequest)
    }
}