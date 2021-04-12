package com.github.labibmuhajir.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.presentation.MainViewModel

class MainFactory(private val movieDataSource: MovieDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(movieDataSource) as T
    }
}