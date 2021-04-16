package com.nero.mint.views

import android.app.Application
import com.nero.mint.repository.Repository

class App : Application() {


    val repository by lazy{

        Repository()


    }


}