package com.github.labibmuhajir

import android.app.Application
import com.github.labibmuhajir.data.api.ApiService
import com.github.labibmuhajir.di.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        InjectEngine.applicationContext = applicationContext
        InjectEngine.apply {
            modules.apply {
                addAll(appModule)
                add(
                    ProvidedObject(
                        OkHttpClient.Builder()
                            .addInterceptor(getInjection<Interceptor>("apiKey"))
                            .addInterceptor(getInjection<Interceptor>("logging"))
                            .build()
                    )
                )
                add(
                    ProvidedObject(
                        Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_URL + "${BuildConfig.API_VERSION}/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getInjection())
                            .build()
                            .create(ApiService::class.java)
                    )
                )
                addAll(repositoryModule)
                addAll(viewModelModule)
                addAll(
                    listOf(
                        ProvidedObject("jakarta", "address"),
                        ProvidedObject("big screen cinema", "cinema")
                    )
                )
            }
        }
    }
}