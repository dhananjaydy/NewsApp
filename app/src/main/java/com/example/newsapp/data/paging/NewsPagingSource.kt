package com.example.newsapp.data.paging

import androidx.paging.PagingSource
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.remote.NewsService
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsService: NewsService,
    private val requestType: NewsRequestType
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        if (requestType is NewsRequestType.Search && requestType.query.isBlank()) {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }

        return try {
            val response = when (requestType) {
                is NewsRequestType.TopHeadlines -> newsService.getTopHeadlines(
                    page = page,
                    pageSize = pageSize
                )
                is NewsRequestType.Category -> newsService.getCategoryHeadlines(
                    category = requestType.categoryName,
                    page = page,
                    pageSize = pageSize
                )
                is NewsRequestType.Search -> newsService.getSearchHeadlines(
                    query = requestType.query,
                    page = page,
                    pageSize = pageSize
                )
            }

            val articles = response.articles

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: androidx.paging.PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}