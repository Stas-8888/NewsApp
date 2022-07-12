package com.example.newsapppp.presentation.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class SaveFragmentViewModel  @Inject constructor(
    private val repository: NewsRepositoryImp,
    private val articleMapper: ArticleMapper
    ): ViewModel() {

    private val _state = MutableLiveData<SaveState>()
    val state: LiveData<SaveState> get() = _state

    fun getAllNews() {
       viewModelScope.launch {
           repository.getAllArticles().collect {
               _state.postValue(SaveState.SetArticles(it.map(articleMapper::convertToModel)))
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun delete(article: ArticleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(articleMapper.convertFromModel(article))
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
