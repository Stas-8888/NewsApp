package com.example.newsapppp.presentation.mapper

import com.example.newsapppp.data.model.Article
import com.example.newsapppp.presentation.model.ArticleModel

class ArticleMapper {

    fun convertToModel(data: Article): ArticleModel {
        return ArticleModel(
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

    fun convertFromModel(data: ArticleModel): Article {
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
