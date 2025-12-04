package com.example.newsapp.ui.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val news: Flow<PagingData<Article>> = _query
        .filter { it.trim().isNotEmpty() }
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { searchQuery ->
            newsRepository.getSearchHeadlines(query = searchQuery).flowOn(dispatcherProvider.io)
        }
        .cachedIn(viewModelScope)

    fun updateQuery(q: String) {
        _query.value = q
    }
}