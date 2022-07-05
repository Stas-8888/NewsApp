package com.example.newsapppp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapppp.model.Article

@Database(entities = [Article::class],version = 1)
abstract class NewsRoomDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

}