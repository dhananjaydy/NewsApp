package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.remote.NewsService
import com.example.newsapp.data.paging.NewsPagingSource
import com.example.newsapp.data.paging.NewsRequestType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
): NewsRepository {

    override fun getTopHeadlines(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsService = newsService,
                    requestType = NewsRequestType.TopHeadlines
                )
            }
        ).flow
    }

    override fun getSearchHeadlines(query: String): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsService = newsService,
                    requestType = NewsRequestType.Search(query)
                )
            }
        ).flow
    }

    override fun getCategoryHeadlines(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsService = newsService,
                    requestType = NewsRequestType.Category(categoryName = category)
                )
            }
        ).flow
    }

}

interface NewsRepository {
    fun getTopHeadlines(): Flow<PagingData<Article>>
    fun getSearchHeadlines(query: String): Flow<PagingData<Article>>
    fun getCategoryHeadlines(category: String): Flow<PagingData<Article>>
}


