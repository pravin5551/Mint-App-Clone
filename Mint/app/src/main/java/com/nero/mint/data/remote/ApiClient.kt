package com.nero.mint.data.remote

import com.nero.mint.newsPojo.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {

    @Headers("Accept: application/json")
    @GET("/api/breakingnews")
    suspend fun businessNews(): NewsResponse


}