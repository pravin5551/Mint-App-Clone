package com.nero.mint.data.remote

import com.nero.mint.newsPojo.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {

    @Headers("Accept: application/json")
    @GET("/v2/everything")
    suspend  fun businessNews(@Header("Content-Type")contentType: String,
                              @Query("apiKey")apikey: String,
                               @Query("q")category:String): NewsResponse





}