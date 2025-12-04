package com.example.newsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "saved_articles"
)
data class SavedArticleEntity(

    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Int = 0,

    @Embedded
    val source: SavedSourceEntity,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("title")
    val title: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("url")
    val url: String?,

    @ColumnInfo("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("content")
    val content: String?
)