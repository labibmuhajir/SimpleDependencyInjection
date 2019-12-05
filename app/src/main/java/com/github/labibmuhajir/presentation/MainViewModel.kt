package com.github.labibmuhajir.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.domain.ViewState
import com.github.labibmuhajir.domain.entity.Movie
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieDataSource) : ViewModel() {
    private var page = 1
    val movieState = MutableLiveData<ViewState<List<Movie>>>()

    fun getMovies() {
        viewModelScope.launch {
            movieState.postValue(ViewState.Loading())
            try {
                val response = movieRepository.getPopularMovies(page)
                if (response.totalPages ?: 0 > page) page++
                response.results?.map { Movie(it) }?.let {
                    movieState.postValue(ViewState.Success(it))
                } ?: movieState.postValue(ViewState.Error())
            } catch (e: Exception) {
                movieState.postValue(ViewState.Error(e.message))
            }
        }
    }
}