package com.github.labibmuhajir.data.datasource

import com.github.labibmuhajir.data.entity.Movie

interface MovieDataSource {
    fun getPopularMovies(): List<Movie>
}