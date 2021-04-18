package com.nero.mint.views

import android.app.Application
import com.nero.mint.data.remote.DataBase.NewsArticlesDataBase
import com.nero.mint.repository.Repository

class App : Application() {




    val repository by lazy{
        val newsDAO=NewsArticlesDataBase.getNewsArticlesDatabse(this).getNewsArticlesDao()
        Repository(newsDAO)


    }


}