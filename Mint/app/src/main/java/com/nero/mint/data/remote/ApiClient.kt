package com.nero.mint.data.remote

import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.newsPojo.PremiumResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @Headers("Accept: application/json")
    @GET("/api/breakingnews")
    suspend fun businessNews(): NewsResponse

    @Headers("Accept: application/json")
    @GET("/api/latest")
    suspend  fun latestNews(): NewsResponse



    @Headers("Accept: application/json")
    @GET("/api/newarticle")
    suspend  fun trendingNews(@Header("Content-Type")contentType: String): List<NewArticlesResponse>


    @Headers("Accept: application/json")
    @GET("/api/search")
    suspend  fun premiumNews(@Header("Content-Type")contentType: String,
                             @Query("categoryname") categoryname:String): PremiumResponse



}



//https://newsapi.org/v2/top-headlines?country=us&page=1&apiKey=0b43be1f3dc84b2492d6691164b3edac

//https://newsapi.org/v2/everything?q=latest&apiKey=0b43be1f3dc84b2492d6691164b3edac