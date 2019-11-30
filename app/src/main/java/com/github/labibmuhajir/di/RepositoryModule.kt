package com.github.labibmuhajir.di

import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.data.repository.MovieRepository
import com.github.labibmuhajir.di.InjectEngine.getInjection

val repositoryModule = listOf<Any>(
    MovieRepository(getInjection(), getInjection()) as MovieDataSource
)