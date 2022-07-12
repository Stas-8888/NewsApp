package com.example.newsapppp.presentation.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.repository.NewsRepositoryImp
import com.example.newsapppp.presentation.mapper.ArticleMapper
import com.example.newsapppp.presentation.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel  @Inject constructor(
    private val repository: NewsRepositoryImp,
    private val articleMapper: ArticleMapper
    ): ViewModel() {


    fun insert(article: ArticleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(articleMapper.convertFromModel(article))
        }
    }
    fun delete(article: ArticleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(articleMapper.convertFromModel(article))
        }
    }
}
