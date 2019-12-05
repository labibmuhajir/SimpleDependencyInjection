package com.github.labibmuhajir.domain

sealed class ViewState<T> {
    data class Success<T>(var data: T) : ViewState<T>()
    data class Error<T>(var message: String? = null) : ViewState<T>()
    data class Loading<T>(var progress: Int? = null) : ViewState<T>()
}