package com.nero.mint.repository


import androidx.lifecycle.LiveData
import com.nero.mint.data.remote.ApiClient
import com.nero.mint.data.remote.DataBase.BookmarkEntity
import com.nero.mint.data.remote.DataBase.NewsArticlesEntity
import com.nero.mint.data.remote.DataBase.NewsDAO
import com.nero.mint.data.remote.RetrofitGenerator
import com.nero.mint.data.remote.RetrofitNetworkRequestHandler
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.newsPojo.PremiumResponse
import com.nero.mint.views.Buttons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(val newsDAO: NewsDAO) {


    private var articleItem: List<NewArticlesResponse> = mutableListOf()
    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val Content_type = "application/json"
    val source = "politics"
    val handler = RetrofitNetworkRequestHandler.ResponseHandler()
    val apiKey = "0b43be1f3dc84b2492d6691164b3edac"
    val sortBy = "Popularity"
    val from = "recent"
    val qlatest = "latest"


    val buttons = Buttons()


    fun getButtonsData(): MutableList<String> {
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

        val result = apiClient.premiumNews(Content_type, source)

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


    suspend fun callSearchNews(name: String): RetrofitNetworkRequestHandler.Resource<PremiumResponse> {

        val result = apiClient.premiumNews(Content_type, name)

        try {
            return handler.handleSuccess(result)

        } catch (e: Exception) {
            return handler.handleException(e)
        }

    }


    fun deleteBookmarks(bookmarkEntity: BookmarkEntity) {

        CoroutineScope(Dispatchers.IO).launch {

            newsDAO.deleteBookmarks(bookmarkEntity)

        }

    }

    fun createBookmarks(bookmarkEntity: BookmarkEntity) {

        CoroutineScope(Dispatchers.IO).launch {

            newsDAO.insertBookMarks(bookmarkEntity)

        }


    }

    fun insertArticles(newsArticlesEntity: NewsArticlesEntity) {

        CoroutineScope(Dispatchers.IO).launch {

            newsDAO.insertArticles(newsArticlesEntity)
        }


    }

    fun addLatest(newsArticlesEntity: NewsArticlesEntity) {

        CoroutineScope(Dispatchers.IO).launch {
            newsDAO.insertArticles(newsArticlesEntity)
        }
    }


    fun getNewsArticlesEntity(): LiveData<List<NewsArticlesEntity>> {


        return newsDAO.getArticles()

    }


    fun getNewsBookMarksEntity(): LiveData<List<BookmarkEntity>> {


        return newsDAO.getBookmarks()

    }

}


//https://newsapi.org/v2/top-headlines?country=us&page=1&apiKey=0b43be1f3dc84b2492d6691164b3edac


//https://newsapi.org/v2/everything?q=latest&apiKey=0b43be1f3dc84b2492d6691164b3edac

