package com.example.newsapppp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapppp.utils.BASE_URL
import com.example.newsapppp.data.ApiService
import com.example.newsapppp.db.NewsDao
import com.example.newsapppp.db.NewsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun loggin() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(loggin())
        .build()

    @Provides
    @Singleton
    fun RetrofitInstance(baseUrl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(
            context,
            NewsRoomDatabase::class.java,
            "article_database"
        ).build()

    @Provides
    fun provideArticleDao(appDatabase: NewsRoomDatabase):NewsDao {
        return appDatabase.getNewsDao()
    }
}