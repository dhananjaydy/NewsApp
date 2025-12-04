package com.example.newsapp.ui.homescreen

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.homescreen.pagerscreens.categoryscreen.CategoryScreen
import com.example.newsapp.ui.homescreen.pagerscreens.topheadlines.TopHeadlinesScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Article) -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerTabs = HomePagerTabs.entries
    val pagerState = rememberPagerState(pageCount = { pagerTabs.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp
        ) {
            pagerTabs.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = index)
                        }
                    },
                    text = { Text(stringResource(currentTab.title)) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex.value == index)
                                currentTab.selectedIcon else currentTab.unselectedIcon,
                            contentDescription = ""
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val currentTab = pagerTabs[page]

            when (currentTab) {
                HomePagerTabs.TOP_HEADLINES -> TopHeadlinesScreen(onClick = onClick)
                else -> {
                    CategoryScreen(
                        category = currentTab.route,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

fun openChromeTab(context: Context, url: String?) {
    val customTabsIntent = CustomTabsIntent.Builder().build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}
