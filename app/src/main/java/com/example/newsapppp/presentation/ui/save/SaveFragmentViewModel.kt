package com.example.newsapppp.presentation.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.repository.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveFragmentViewModel  @Inject constructor(private val repository: RetrofitRepository): ViewModel() {

    fun getAllNews(): LiveData<List<Article>> {
        return repository.getAllArticles()
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }
    fun deleteAll() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAll()
    }
}