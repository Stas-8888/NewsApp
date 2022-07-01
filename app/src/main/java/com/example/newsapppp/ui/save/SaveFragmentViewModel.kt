package com.example.newsapppp.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsapppp.db.NewsDao
import com.example.newsapppp.db.NewsRoomDatabase
import com.example.newsapppp.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveFragmentViewModel(var dao: NewsDao): ViewModel() {

    fun getAllNews(): LiveData<List<Article>> {
        return dao.getAllArticles()
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteArticle(article)
        }
    }
    fun deleteAll() = viewModelScope.launch (Dispatchers.IO){
        dao.deleteAll()
    }
    class MainViewModelFactory(val dao: NewsDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SaveFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SaveFragmentViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}