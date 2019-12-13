package com.github.labibmuhajir.di

import com.github.labibmuhajir.BuildConfig
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

val appModule = listOf(
    ProvidedObject(Gson()),
    ProvidedObject(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            val url: HttpUrl =
                request.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }, "apiKey"),
    ProvidedObject(
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
        "logging"
    )
)