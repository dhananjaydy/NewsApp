package com.example.newsapp.data.local

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedArticlesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveArticle(article: SavedArticleEntity)

    @Delete
    suspend fun deleteArticle(article: SavedArticleEntity)

    @Query("SELECT * FROM saved_articles ORDER BY id DESC")
    fun getSavedArticles(): PagingSource<Int, SavedArticleEntity>

    @Query("SELECT * FROM saved_articles WHERE url = :articleUrl")
    fun getArticlesByUrl(articleUrl: String): Flow<List<SavedArticleEntity>>

    @Query("SELECT * FROM saved_articles WHERE url = :articleUrl LIMIT 1")
    suspend fun getArticleByUrl(articleUrl: String): SavedArticleEntity?

}