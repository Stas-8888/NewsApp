package com.example.newsapppp.presentation.mapper

import com.example.newsapppp.data.model.NewsResponse

class NewsResponseMapper {

    fun converterToNewsResponseModel(data: NewsResponse): com.example.newsapppp.presentation.model.NewsResponseModel {
        return com.example.newsapppp.presentation.model.NewsResponseModel(
            articles = data.articles,
            status = data.status,
            totalResults = data.totalResults
        )
    }

    fun converterFromResponseModel(data: com.example.newsapppp.presentation.model.NewsResponseModel): NewsResponse {
        return NewsResponse(
            articles = data.articles,
            status = data.status,
            totalResults = data.totalResults
        )
    }
}
