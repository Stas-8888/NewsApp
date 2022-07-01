package com.example.newsapppp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapppp.model.Article

@Database(entities = [Article::class],version = 1)
abstract class NewsRoomDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsRoomDatabase? = null

        fun getDatabase(context: Context): NewsRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDatabase::class.java, "news_db.db"
                ).build()
                instance
            }
        }
    }
}