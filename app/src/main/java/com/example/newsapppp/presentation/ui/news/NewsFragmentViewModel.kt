package com.example.newsapppp.presentation.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel  @Inject constructor(
    private val repository: NewsRepository,

    ): ViewModel() {


    fun insert(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(article)
        }
    }
    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }
}
