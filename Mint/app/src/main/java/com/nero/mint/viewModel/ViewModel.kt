package com.nero.mint.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nero.mint.newsPojo.NewsResponse
import com.nero.mint.repository.Repository
import com.nero.mint.data.remote.RetrofitNetworkRequestHandler
import kotlinx.coroutines.Dispatchers

class MyViewModel(val respository:Repository) : ViewModel() {

    val repository = Repository()

    var buttonsList = MutableLiveData<List<String>>()



    fun getButtonData(): MutableLiveData<List<String>> {

        var data = repository.getButtonsData()

        buttonsList.value = data

        return buttonsList
    }



    fun callBusinessApi(): LiveData<RetrofitNetworkRequestHandler.Resource<NewsResponse>> {

        return liveData(Dispatchers.IO) {
            val result=   repository.callBusinessApi()

            emit(result)

        }
    }




}