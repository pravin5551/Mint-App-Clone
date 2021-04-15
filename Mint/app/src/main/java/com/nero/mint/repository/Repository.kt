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
    private val handler = RetrofitNetworkRequestHandler.ResponseHandler()
    val sortBy="Popularity"
    val from ="recent"

    private val buttons = Buttons()


    fun getButtonsData():MutableList<String>{
        return buttons.buildButtonsData()
    }


    suspend fun callBusinessApi(): RetrofitNetworkRequestHandler.Resource<NewsResponse> {

        val result = apiClient.businessNews()

        return try {

            handler.handleSuccess(result)

        } catch (e: Exception) {

            handler.handleException(e)
        }

    }





}