package com.example.newsapppp.presentation.ui.save

import com.example.newsapppp.presentation.model.ArticleModel

sealed class SaveState {

    data class SetArticles(val articles: List<ArticleModel>) : SaveState()

}
