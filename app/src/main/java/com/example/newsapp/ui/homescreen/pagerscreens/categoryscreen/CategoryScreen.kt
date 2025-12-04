package com.example.newsapp.ui.homescreen.pagerscreens.categoryscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.common.ArticleListContent

@Composable
fun CategoryScreen(
    category: String,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel: CategoryViewModel = hiltViewModel(
        key = category,
        creationCallback = { factory: CategoryViewModel.Factory ->
            factory.create(category)
        }
    )

    val pagedArticles = viewModel.categoryNews.collectAsLazyPagingItems()

    ArticleListContent(
        pagingItems = pagedArticles,
        onClick = onClick
    )
}
