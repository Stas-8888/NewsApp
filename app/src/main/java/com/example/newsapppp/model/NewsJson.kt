package com.example.newsapppp.model


data class NewsJson(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)