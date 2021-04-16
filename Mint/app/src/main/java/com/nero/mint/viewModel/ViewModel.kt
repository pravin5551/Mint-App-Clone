package com.nero.mint.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nero.mint.repository.Repository
import com.nero.mint.data.remote.RetrofitNetworkRequestHandler
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.newsPojo.PremiumResponse
import kotlinx.coroutines.Dispatchers

class MyViewModel(val respository:Repository) : ViewModel() {

    val repository = Repository()

    var buttonsList = MutableLiveData<List<String>>()



    fun getButtonData(): MutableLiveData<List<String>> {

        var data = repository.getButtonsData()

        buttonsList.value = data

        return buttonsList
    }



    fun callTrendingApi(): LiveData<RetrofitNetworkRequestHandler.Resource<List<NewArticlesResponse>>> {

        return liveData(Dispatchers.IO) {


            val result=   repository.callTrendingApi()

            emit(result)

        }
    }

    fun callBusinessApi(): LiveData<RetrofitNetworkRequestHandler.Resource<NewsResponse>> {

        return liveData(Dispatchers.IO) {
            val result=   repository.callBusinessApi()

            emit(result)

        }
    }


    fun callLatestNews(): LiveData<RetrofitNetworkRequestHandler.Resource<NewsResponse>> {
        return liveData(Dispatchers.IO) {
            val result=   repository.callLatestNews()

            emit(result)

        }
    }



    fun callPremiumApi(): LiveData<RetrofitNetworkRequestHandler.Resource<PremiumResponse>>{

        return liveData(Dispatchers.IO) {


            val result=   repository.callPremiumApi()

            emit(result)

        }
    }


    fun callSearchButtonNews(name:String): LiveData<RetrofitNetworkRequestHandler.Resource<PremiumResponse>>{

        return liveData(Dispatchers.IO) {


            val result=   repository.callSearchNews(name)

            emit(result)

        }
    }




}