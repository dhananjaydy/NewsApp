package com.example.newsapp.ui.homescreen.pagerscreens.categoryscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

@HiltViewModel(assistedFactory = CategoryViewModel.Factory::class)
class CategoryViewModel @AssistedInject constructor(
    newsRepository: NewsRepository,
    @Assisted private val category: String
) : ViewModel() {

    val categoryNews: Flow<PagingData<Article>> =
        newsRepository.getCategoryHeadlines(category).cachedIn(viewModelScope)

    @AssistedFactory
    interface Factory {
        fun create(category: String): CategoryViewModel
    }
}
