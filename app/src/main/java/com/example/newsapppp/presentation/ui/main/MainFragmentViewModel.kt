package com.example.newsapppp.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.NewsResponse
import com.example.newsapppp.data.repository.NewsRepository
import com.example.newsapppp.presentation.utils.Resource
import com.example.newsapppp.presentation.utils.categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: NewsRepository
    ) : ViewModel() {
    val newsResponse: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun getNews(countryCode:String,category:String) =
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getNews2(countryCode = countryCode,category=category)
            if (response.isSuccessful) {
                response.body().let {
                    newsResponse.postValue(Resource.Success(it))
                }
            }
        }

    fun getCategory(category: String) =
        viewModelScope.launch {
            val response = repository.getCategory(category = category)
            if (response.isSuccessful) {
                response.body().let {
                    newsResponse.postValue(Resource.Success(it))
                }
            }
        }
}
