package com.nero.mint.repository

import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.views.Buttons
import com.nero.mint.data.remote.ApiClient
import com.nero.mint.data.remote.RetrofitGenerator
import com.nero.mint.data.remote.RetrofitNetworkRequestHandler
import java.lang.Exception

class Repository {


    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val Content_type = "application/json"
    val source = "Business News"
    val handler = RetrofitNetworkRequestHandler.ResponseHandler()
    val apiKey = "0b43be1f3dc84b2492d6691164b3edac"
    val sortBy="Popularity"
    val from ="recent"
    val qlatest = "latest"


    val buttons = Buttons()


    fun getButtonsData():MutableList<String>{
        return buttons.buildButtonsData()
    }


    suspend fun callBusinessApi(): RetrofitNetworkRequestHandler.Resource<NewsResponse> {

        val result = apiClient.businessNews(Content_type,apiKey,source)

        try {

            return handler.handleSuccess(result)

        } catch (e: Exception) {

            return handler.handleException(e)
        }

    }


    suspend fun callLatestNews(): RetrofitNetworkRequestHandler.Resource<NewsResponse> {

        val result = apiClient.latestNews(Content_type,apiKey,qlatest)

        try {

            return handler.handleSuccess(result)

        } catch (e: Exception) {

            return handler.handleException(e)
        }

    }


}




//https://newsapi.org/v2/top-headlines?country=us&page=1&apiKey=0b43be1f3dc84b2492d6691164b3edac

//https://newsapi.org/v2/everything?q=latest&apiKey=0b43be1f3dc84b2492d6691164b3edac