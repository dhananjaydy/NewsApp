package com.example.newsapp.ui.searchscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.base.NewsBottomTabs
import com.example.newsapp.ui.common.ArticleListContent
import com.example.newsapp.ui.common.EmptySearchState
import com.example.newsapp.ui.common.SearchWidget
import com.example.newsapp.ui.homescreen.openChromeTab

fun NavGraphBuilder.searchScreen() {
    composable(NewsBottomTabs.Search.route) {
        val context = LocalContext.current
        SearchScreen(
            onClick = { article ->
                article.url?.let {
                    openChromeTab(
                        context = context,
                        url = article.url
                    )
                } ?: run {
                    Toast.makeText(
                        context,
                        "Sorry, article link is not available.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}

@Composable
fun SearchScreen(
    onClick: (Article) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.news.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsState()

    Scaffold(
        topBar = {
            SearchWidget(
                text = query,
                onTextChange = { viewModel.updateQuery(it) }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            if (query.isEmpty()) {
                EmptySearchState(
                    emptyStateMeaage = stringResource(R.string.type_to_search_for_news)
                )
            } else {
                ArticleListContent(
                    pagingItems = pagingItems,
                    onClick = onClick
                )
            }
        }
    }
}