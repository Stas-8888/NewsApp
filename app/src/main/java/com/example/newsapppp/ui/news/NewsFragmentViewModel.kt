package com.example.newsapppp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.RetrofitRepository
import com.example.newsapppp.db.NewsRoomDatabase
import com.example.newsapppp.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel  @Inject constructor(private val repository: RetrofitRepository): ViewModel() {


    fun insert(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(article)
        }
    }
}