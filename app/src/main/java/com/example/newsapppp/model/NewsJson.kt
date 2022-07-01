package com.example.newsapppp.model


import com.google.gson.annotations.SerializedName

data class NewsJson(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)