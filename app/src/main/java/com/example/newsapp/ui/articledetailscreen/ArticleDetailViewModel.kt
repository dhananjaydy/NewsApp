package com.example.newsapp.ui.articledetailscreen

// Make sure this is your PARCELABLE domain model
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.repository.SavedArticlesRepository
import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val savedArticlesRepository: SavedArticlesRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val article = savedStateHandle.getStateFlow<Article?>("article", null)



    fun onSaveToggle(article: Article) {
        viewModelScope.launch(dispatcherProvider.io) {
            savedArticlesRepository.saveArticle(article = article)
        }
    }
}
