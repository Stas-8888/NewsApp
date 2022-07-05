package com.example.newsapppp.ui.search

import androidx.lifecycle.*
import com.example.newsapppp.data.RetrofitRepository
import com.example.newsapppp.model.NewsJson
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