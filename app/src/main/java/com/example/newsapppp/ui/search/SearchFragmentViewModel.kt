package com.example.newsapppp.ui.search

import androidx.lifecycle.*
import com.example.newsapppp.data.RetrofitRepository
import com.example.newsapppp.db.NewsRoomDatabase
import com.example.newsapppp.model.Article
import com.example.newsapppp.model.NewsJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchFragmentViewModel(database: NewsRoomDatabase): ViewModel() {
    val repository = RetrofitRepository()
    val searchNews: MutableLiveData<Response<NewsJson>> = MutableLiveData()
    var searchNewsPage = 1

    fun getSearchRetrofit(searchQuery: String){
        viewModelScope.launch {
            searchNews.value = repository.searchNews(searchQuery,searchNewsPage)
        }
    }

    class MainViewModelFactory(val database: NewsRoomDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchFragmentViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}