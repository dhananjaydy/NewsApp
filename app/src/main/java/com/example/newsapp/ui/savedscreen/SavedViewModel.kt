package com.example.newsapp.ui.savedscreen

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.repository.SavedArticlesRepository
import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val savedArticlesRepository: SavedArticlesRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    val savedArticles = savedArticlesRepository
        .getSavedArticles()
        .flowOn(dispatcherProvider.io)
        .cachedIn(viewModelScope)

    fun onArticleSwiped(article: Article) {
        viewModelScope.launch(context = dispatcherProvider.io) {
            savedArticlesRepository.deleteArticle(article)
        }
    }

}