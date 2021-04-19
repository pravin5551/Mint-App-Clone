package com.nero.mint.data.remote

import com.nero.mint.data.remote.SearchPojo.SearchResponse
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.newsPojo.PremiumResponse
import retrofit2.Call
import retrofit2.http.*

interface  ApiClient {

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


    @Headers("Accept: application/json")
    @GET("/api/search")
    suspend  fun ButtonSearch(@Header("Content-Type")contentType: String,
                             @Query("categoryname") categoryname:String): PremiumResponse


    @Headers("Accept: application/json")
    @GET("/v2/everything")
    suspend  fun callSearch(@Header("Content-Type")contentType: String,
                            @Query("q")search :String,
                            @Query("from")from: String, @Query("sortBy")sortBy: String,
                            @Query("apiKey")apikey: String): SearchResponse





}

