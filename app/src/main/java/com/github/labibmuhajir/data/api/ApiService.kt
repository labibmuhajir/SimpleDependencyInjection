package com.github.labibmuhajir.data.api

import com.github.labibmuhajir.data.api.response.PageableResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovie(@Query("page") page: Int): PageableResponse
}