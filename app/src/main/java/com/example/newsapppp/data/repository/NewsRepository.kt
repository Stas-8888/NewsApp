package com.example.newsapppp.data.repository

import com.example.newsapppp.data.db.NewsDao
import com.example.newsapppp.data.model.Article
import com.example.newsapppp.data.model.NewsResponse
import com.example.newsapppp.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {

    suspend fun insert(article: Article) = newsDao.insert(article)

    suspend fun delete(article: Article) = newsDao.deleteArticle(article)

    suspend fun deleteAll() = newsDao.deleteAll()

    fun getAllArticles() = newsDao.getAllArticles()

    suspend fun getNews() = apiService.getBreakingNews()

    suspend fun getNews2(countryCode: String,category:String) =
        apiService.getBreakingNews(countryCode = countryCode,category=category)

    suspend fun getCategory(category: String) =
        apiService.getBreakingNews(countryCode = category)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        apiService.searchForNews(searchQuery, pageNumber)
}
