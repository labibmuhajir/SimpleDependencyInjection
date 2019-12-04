package com.github.labibmuhajir.data.api.response

import com.google.gson.annotations.SerializedName

data class PageableResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieResponse>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)