package com.example.newsapppp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapppp.data.model.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}