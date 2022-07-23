package com.example.newsapppp.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: NewsRepository,
) :
    ViewModel() {

    val searchNews: MutableLiveData<List<Article>> = MutableLiveData()
    var searchNewsPage = 1

    fun getSearchRetrofit(searchQuery: String) {
        viewModelScope.launch {
            searchNews.value = repository.searchNews(searchQuery, searchNewsPage).body()!!.articles

        }
    }
}
