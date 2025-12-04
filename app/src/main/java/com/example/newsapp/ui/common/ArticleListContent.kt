package com.example.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.Article

@Composable
fun ArticleListContent(
    pagingItems: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

    val isListEmpty = pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0
    if (isListEmpty) {
        EmptySearchState(
            emptyStateMeaage = stringResource(R.string.no_headlines_found_right_now)
        )
        return
    }
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(count = pagingItems.itemCount) { index ->
            val article = pagingItems[index]
            if (article != null) {
                ArticleItem(
                    article = article,
                    onClick = { onClick(article) }
                )
            }
        }

        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage ?: stringResource(R.string.unknown_error),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.wrapContentHeight()) }
                }

                loadState.append is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.wrapContentHeight(),
                            message = error.error.localizedMessage ?: stringResource(R.string.error_loading_more),
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}