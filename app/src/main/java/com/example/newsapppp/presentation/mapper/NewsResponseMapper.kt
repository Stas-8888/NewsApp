//package com.example.newsapppp.presentation.mapper
//
//import com.example.newsapppp.data.model.NewsResponse
//import com.example.newsapppp.presentation.model.NewsResponseModel
//
//class NewsResponseMapper {
//
//    fun converterToNewsResponseModel(data: NewsResponse): NewsResponseModel {
//        return NewsResponseModel(
//            articles = data.articles,
//            status = data.status,
//            totalResults = data.totalResults
//        )
//    }
//
//    fun converterFromResponseModel(data: NewsResponseModel): NewsResponse {
//        return NewsResponse(
//            articles = data.articles,
//            status = data.status,
//            totalResults = data.totalResults
//        )
//    }
//}
