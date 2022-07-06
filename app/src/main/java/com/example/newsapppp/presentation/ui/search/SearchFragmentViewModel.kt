package com.example.newsapppp.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.NewsJson
import com.example.newsapppp.data.repository.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel  @Inject constructor(private val repository: RetrofitRepository): ViewModel() {

    val searchNews: MutableLiveData<Response<NewsJson>> = MutableLiveData()
    var searchNewsPage = 1

    fun getSearchRetrofit(searchQuery: String){
        viewModelScope.launch {
            searchNews.value = repository.searchNews(searchQuery,searchNewsPage)
        }
    }
}