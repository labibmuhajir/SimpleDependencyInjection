package com.github.labibmuhajir.domain.entity

import com.github.labibmuhajir.data.api.response.MovieResponse

data class Movie(
    val posterPath: String?,
    val id: Int?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val title: String?,
    val overview: String?,
    val releaseDate: String?
) {
    constructor(movieResponse: MovieResponse) : this(
        movieResponse.posterPath,
        movieResponse.id,
        movieResponse.backdropPath,
        movieResponse.originalLanguage,
        movieResponse.originalTitle,
        movieResponse.title,
        movieResponse.overview,
        movieResponse.releaseDate
    )
}