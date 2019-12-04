package com.github.labibmuhajir.data.datasource

import com.github.labibmuhajir.data.api.response.PageableResponse

interface MovieDataSource {
    suspend fun getPopularMovies(page: Int): PageableResponse
}