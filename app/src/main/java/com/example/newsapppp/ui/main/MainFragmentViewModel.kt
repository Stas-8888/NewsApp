package com.example.newsapppp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.RetrofitRepository
import com.example.newsapppp.model.NewsJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel  @Inject constructor(private val repository: RetrofitRepository): ViewModel() {

    val myNews: MutableLiveData<Response<NewsJson>> = MutableLiveData()

    fun getNewsRetrofit(){
        viewModelScope.launch {
            myNews.value = repository.getNews()
        }
    }

}