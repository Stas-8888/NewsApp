package com.example.newsapppp.data.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val author: String?,
    @ColumnInfo
    val content: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val publishedAt: String?,
//    val source: Source?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val url: String?,
    @ColumnInfo
    val urlToImage: String?
) : Parcelable
