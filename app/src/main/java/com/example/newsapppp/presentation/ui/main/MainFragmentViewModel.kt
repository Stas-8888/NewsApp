package com.example.newsapppp.presentation.ui.main

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
class MainFragmentViewModel @Inject constructor(
    private val repository: RetrofitRepository,
    private val articleMapper: ArticleMapper
) : ViewModel() {

    val myNews: MutableLiveData<List<ArticleModel>> = MutableLiveData()

    fun getNewsRetrofit() {
        viewModelScope.launch {
            myNews.value = repository.getNews().body()!!.articles.map(articleMapper::convertToModel)
        }
    }
}
