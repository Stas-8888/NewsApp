package com.example.newsapppp.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.repository.RetrofitRepository
import com.example.newsapppp.presentation.mapper.ArticleMapper
import com.example.newsapppp.presentation.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: RetrofitRepository,
    private val articleMapper: ArticleMapper
) :
    ViewModel() {

    val searchNews: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    var searchNewsPage = 1

    fun getSearchRetrofit(searchQuery: String) {
        viewModelScope.launch {
            searchNews.value =
                repository.searchNews(searchQuery, searchNewsPage).body()!!.articles.map(
                    articleMapper::convertToModel
                )
        }
    }
}
