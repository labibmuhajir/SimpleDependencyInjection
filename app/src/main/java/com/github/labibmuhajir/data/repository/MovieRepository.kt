package com.github.labibmuhajir.data.repository

import android.content.Context
import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.data.entity.Movie
import com.github.labibmuhajir.ext.loadJsonFromAsset
import com.google.gson.Gson

class MovieRepository(private val context: Context, private val gson: Gson) : MovieDataSource {
    private val popularMovie = "popular_movies.json"

    override fun getPopularMovies(): List<Movie> {
        return gson.fromJson(context.loadJsonFromAsset(popularMovie), Array<Movie>::class.java)
            .toList()
    }

}