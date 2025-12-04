package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsapp.data.local.SavedArticleEntity
import com.example.newsapp.data.local.SavedArticlesDao
import com.example.newsapp.data.local.SavedSourceEntity
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.remote.Source
import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import com.example.newsapp.data.paging.NewsPagingSource
import com.example.newsapp.data.paging.NewsRequestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


interface SavedArticlesRepository {
    fun getSavedArticles(): Flow<PagingData<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun isArticleSaved(url: String): Flow<Boolean>
}

@Singleton
class SavedArticlesRepositoryImpl @Inject constructor(
    private val savedArticlesDao: SavedArticlesDao,
) : SavedArticlesRepository {

    override fun getSavedArticles(): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                savedArticlesDao.getSavedArticles()
            }
        ).flow.map {
            it.map { entity ->  entity.toDomain() }
        }
    }

    override suspend fun saveArticle(article: Article) {
        savedArticlesDao.saveArticle(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        val articleUrl = article.url
        if (articleUrl != null) {
            val entityToDelete = savedArticlesDao.getArticleByUrl(articleUrl)
            if (entityToDelete != null) {
                savedArticlesDao.deleteArticle(entityToDelete)
            }
        }
    }

    override fun isArticleSaved(url: String): Flow<Boolean> {
        return savedArticlesDao.getArticlesByUrl(url)
            .map { it.isNotEmpty() }
    }

}


fun SavedArticleEntity.toDomain(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(
            id = this.source.id,
            name = this.source.name
        ),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun Article.toEntity(): SavedArticleEntity {
    return SavedArticleEntity(

        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = SavedSourceEntity(
            id = this.source.id,
            name = this.source.name
        ),
        title = this.title ?: "No Title",
        url = this.url ?: "",
        urlToImage = this.urlToImage
    )
}
