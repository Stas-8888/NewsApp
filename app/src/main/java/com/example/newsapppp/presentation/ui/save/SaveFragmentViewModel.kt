package com.example.newsapppp.presentation.ui.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveFragmentViewModel  @Inject constructor(
    private val repository: NewsRepository,
    ): ViewModel() {

//    private val _state = MutableLiveData<SaveState>()
//    val state: LiveData<SaveState> get() = _state

    private val _state = MutableStateFlow<List<Article>>(emptyList())
    val state: StateFlow<List<Article>> = _state

    fun getAllNews() {
       viewModelScope.launch (Dispatchers.IO){
           repository.getAllArticles().collect {
               _state.emit(it)
//               _state.postValue(SaveState.SetArticles(it.map(articleMapper::convertToModel)))
           }
       }
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
