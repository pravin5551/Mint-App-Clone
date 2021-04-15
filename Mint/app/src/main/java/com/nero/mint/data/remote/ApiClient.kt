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

    @Headers("Accept: application/json")
    @GET("/v2/everything")
    suspend  fun latestNews(@Header("Content-Type")contentType: String,
                              @Query("apiKey")apikey: String,
                              @Query("q")category:String): NewsResponse



}


//https://newsapi.org/v2/top-headlines?country=us&page=1&apiKey=0b43be1f3dc84b2492d6691164b3edac

//https://newsapi.org/v2/everything?q=latest&apiKey=0b43be1f3dc84b2492d6691164b3edac