package com.example.newsapp.ui.savedscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.base.NewsBottomTabs
import com.example.newsapp.ui.common.ArticleItem
import com.example.newsapp.ui.common.EmptySearchState
import com.example.newsapp.ui.common.PageLoader
import com.example.newsapp.ui.homescreen.openChromeTab


fun NavGraphBuilder.savedScreen() {
    composable(NewsBottomTabs.Saved.route) {
        val context = LocalContext.current
        SavedScreen(
            onClick = { article ->
                article.url?.let {
                    openChromeTab(
                        context = context,
                        url = article.url
                    )
                } ?: run {
                    Toast.makeText(
                        context,
                        context.getString(R.string.sorry_article_link_is_not_available),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen(
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel()
) {
    val savedArticles = viewModel.savedArticles.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        if (savedArticles.loadState.refresh is LoadState.NotLoading && savedArticles.itemCount == 0) {
            EmptySearchState(emptyStateMeaage = stringResource(R.string.no_saved_articles_yet))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = savedArticles.itemCount,
                    key = { index -> savedArticles.peek(index)?.url ?: index }
                ) { index ->
                    val article = savedArticles[index]
                    if (article != null) {

                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = {
                                if (it == EndToStart) {
                                    viewModel.onArticleSwiped(article)
                                    return@rememberSwipeToDismissBoxState true
                                }
                                false
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.Red)
                                        .padding(horizontal = 24.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.delete),
                                        tint = Color.White
                                    )
                                }
                            }
                        ) {
                            ArticleItem(
                                article = article,
                                onClick = { onClick(article) }
                            )
                        }
                    }
                }
                if (savedArticles.loadState.append is LoadState.Loading) {
                    item { PageLoader(modifier = Modifier.fillParentMaxWidth()) }
                }
            }
        }
        if (savedArticles.loadState.refresh is LoadState.Loading) {
            PageLoader(modifier = Modifier.fillMaxSize())
        }
    }
}
