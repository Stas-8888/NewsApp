package com.example.newsapppp

import android.app.Application
import com.example.newsapppp.db.NewsRoomDatabase

class Application: Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NewsRoomDatabase.getDatabase(this) }
    val daoNews by lazy { database.getNewsDao() }
}