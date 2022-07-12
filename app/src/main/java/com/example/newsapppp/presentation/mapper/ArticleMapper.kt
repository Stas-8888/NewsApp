package com.example.newsapppp.presentation.mapper

import com.example.newsapppp.data.model.Article

class ArticleMapper {

    fun convertToModel(data: Article): com.example.newsapppp.presentation.model.ArticleModel {
        return com.example.newsapppp.presentation.model.ArticleModel(
            id = data.id,
            author = data.author,
            content = data.content,
            description = data.description,
            publishedAt = data.publishedAt,
            title = data.title,
            url = data.url,
            urlToImage = data.urlToImage
        )
    }

    fun convertFromModel(data: com.example.newsapppp.presentation.model.ArticleModel): Article {
        return Article(
            id = data.id,
            author = data.author,
            content = data.content,
            description = data.description,
            publishedAt = data.publishedAt,
            title = data.title,
            url = data.url,
            urlToImage = data.urlToImage
        )
    }
}
