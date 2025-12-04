package com.example.newsapp.ui.base

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.R

interface NavigationItem {
    val route: String
    @get:StringRes
    val title: Int
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}

enum class NewsBottomTabs(
    override val route: String,
    @StringRes override val title: Int,
    override val selectedIcon: ImageVector,
    override val unselectedIcon: ImageVector,
    val showBottomBar: Boolean
) : NavigationItem {

    Home(
        route = "home",
        title = R.string.home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        showBottomBar = true
    ),
    Search(
        route = "search",
        title = R.string.search,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        showBottomBar = true
    ),
    Saved(
        route = "saved",
        title = R.string.saved,
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Outlined.Star,
        showBottomBar = true
    ),
    ArticleDetails(
        route = "article_detail",
        title = R.string.article_detail,
        selectedIcon = Icons.Filled.Newspaper,
        unselectedIcon = Icons.Outlined.Newspaper,
        showBottomBar = false
    )
}




