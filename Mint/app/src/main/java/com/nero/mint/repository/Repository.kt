package com.nero.mint.repository


import android.util.Log
import com.nero.mint.views.Buttons
import com.nero.mint.data.remote.ApiClient
import com.nero.mint.data.remote.RetrofitGenerator
import com.nero.mint.data.remote.RetrofitNetworkRequestHandler
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.newsPojo.PremiumResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Repository {


    private var articleItem :List<NewArticlesResponse> = mutableListOf()
    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val Content_type = "application/json"
    val source = "politics"
    val handler = RetrofitNetworkRequestHandler.ResponseHandler()
    val apiKey = "0b43be1f3dc84b2492d6691164b3edac"
    val sortBy="Popularity"
    val from ="recent"
    val qlatest = "latest"


    val buttons = Buttons()


    fun getButtonsData():MutableList<String>{
        return buttons.buildButtonsData()
    }


    suspend fun callTrendingApi(): RetrofitNetworkRequestHandler.Resource<List<NewArticlesResponse>> {

        val result = apiClient.trendingNews(Content_type)

        try {
            return handler.handleSuccess(result)

        } catch (e: Exception) {
            return handler.handleException(e)
        }

    }


    suspend fun callBusinessApi(): RetrofitNetworkRequestHandler.Resource<NewsResponse> {

        val result = apiClient.businessNews()

        return try {

            handler.handleSuccess(result)

        } catch (e: Exception) {

            handler.handleException(e)
        }

    }

    suspend fun callPremiumApi(): RetrofitNetworkRequestHandler.Resource<PremiumResponse> {

        val result = apiClient.premiumNews(Content_type,source)

        try {
            return handler.handleSuccess(result)

        } catch (e: Exception) {
            return handler.handleException(e)
        }

    }


    suspend fun callLatestNews(): RetrofitNetworkRequestHandler.Resource<NewsResponse> {

        val result = apiClient.latestNews()

        try {

            return handler.handleSuccess(result)

        } catch (e: Exception) {

            return handler.handleException(e)
        }

    }



    suspend fun callSearchNews(name:String): RetrofitNetworkRequestHandler.Resource<PremiumResponse> {

        val result = apiClient.premiumNews(Content_type,name)

        try {
            return handler.handleSuccess(result)

        } catch (e: Exception) {
            return handler.handleException(e)
        }

    }




}




//https://newsapi.org/v2/top-headlines?country=us&page=1&apiKey=0b43be1f3dc84b2492d6691164b3edac





//https://newsapi.org/v2/everything?q=latest&apiKey=0b43be1f3dc84b2492d6691164b3edac

