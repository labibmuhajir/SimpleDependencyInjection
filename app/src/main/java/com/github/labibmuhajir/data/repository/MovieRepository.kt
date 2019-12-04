package com.github.labibmuhajir.data.repository

import com.github.labibmuhajir.data.api.ApiService
import com.github.labibmuhajir.data.api.response.PageableResponse
import com.github.labibmuhajir.data.datasource.MovieDataSource

class MovieRepository(private val apiService: ApiService) : MovieDataSource {
    override suspend fun getPopularMovies(page: Int): PageableResponse {
        return apiService.getMovie(page)
    }
}