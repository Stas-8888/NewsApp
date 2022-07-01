package com.example.newsapppp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.db.NewsRoomDatabase
import com.example.newsapppp.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFragmentViewModel(database: NewsRoomDatabase): ViewModel() {
    val dao = database.getNewsDao()

    fun insert(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(article)
        }
    }

    class MainViewModelFactory(val database: NewsRoomDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsFragmentViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}