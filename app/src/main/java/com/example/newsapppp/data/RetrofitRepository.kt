package com.example.newsapppp.data

import com.example.newsapppp.model.NewsJson
import retrofit2.Response

class RetrofitRepository {
    suspend fun getNews():Response<NewsJson>{
        return RetrofitInstance.api.getBreakingNews()
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int):Response<NewsJson>{
        return RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
    }
}