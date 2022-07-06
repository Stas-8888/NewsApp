package com.example.newsapppp.data.repository

import com.example.newsapppp.data.db.NewsDao
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.model.NewsJson
import com.example.newsapppp.data.retrofit.ApiService
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {

    suspend fun getNews(): Response<NewsJson> {
        return apiService.getBreakingNews()
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsJson> {
        return apiService.searchForNews(searchQuery, pageNumber)
    }

    fun getAllArticles() = newsDao.getAllArticles()

    suspend fun insert(article: Article) {
        newsDao.insert(article)
    }

    suspend fun delete(article: Article) {
        newsDao.deleteArticle(article)
    }

    suspend fun deleteAll() {
        newsDao.deleteAll()
    }
}

