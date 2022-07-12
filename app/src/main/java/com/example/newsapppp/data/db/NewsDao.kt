package com.example.newsapppp.data.db

import androidx.room.*
import com.example.newsapppp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}
