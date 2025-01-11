package com.example.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.newsapp.repository.NewsRepository

class NewsVMProviderFactory(val app:Application,val newsRepository: NewsRepository): ViewModelProvider.Factory {
    override fun<T: ViewModel> create(modelClass:Class<T>):T{
        return NewsViewModel(app,newsRepository) as T
    }
}