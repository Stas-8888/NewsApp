package com.example.newsapppp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.RetrofitRepository
import com.example.newsapppp.db.NewsRoomDatabase
import com.example.newsapppp.model.NewsJson
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(database: NewsRoomDatabase): ViewModel() {
    val repository = RetrofitRepository()
    val myNews: MutableLiveData<Response<NewsJson>> = MutableLiveData()

    fun getNewsRetrofit(){
        viewModelScope.launch {
            myNews.value = repository.getNews()
        }
    }

    class MainViewModelFactory(val database: NewsRoomDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainFragmentViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}