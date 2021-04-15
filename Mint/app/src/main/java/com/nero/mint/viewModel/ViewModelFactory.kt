package com.nero.mint.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nero.mint.repository.Repository

class ViewModelFactory(val respository :Repository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(respository) as T
    }
}