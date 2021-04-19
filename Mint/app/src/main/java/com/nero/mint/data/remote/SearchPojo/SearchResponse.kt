package com.nero.mint.data.remote.SearchPojo

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,

    @field:SerializedName("status")
    val status: String
)