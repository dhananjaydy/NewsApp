package com.example.newsapp.ui.homescreen.pagerscreens.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    val topHeadlines: Flow<PagingData<Article>> = newsRepository
        .getTopHeadlines()
        .flowOn(dispatcherProvider.io)
        .cachedIn(viewModelScope)

}