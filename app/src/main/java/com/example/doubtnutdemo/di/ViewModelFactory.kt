package com.example.doubtnutdemo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.ui.NewsListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val repository: NewsListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}