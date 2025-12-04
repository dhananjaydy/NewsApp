package com.example.newsapp.ui.homescreen.pagerscreens.topheadlines

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.common.ArticleListContent

@Composable
fun TopHeadlinesScreen(
    onClick: (Article) -> Unit,
    viewModel: TopHeadlinesViewModel = hiltViewModel()
) {

    val topHeadlines = viewModel.topHeadlines.collectAsLazyPagingItems()

    ArticleListContent(
        pagingItems = topHeadlines,
        onClick = { onClick(it) }
    )
}