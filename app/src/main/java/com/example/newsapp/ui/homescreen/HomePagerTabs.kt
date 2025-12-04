package com.example.newsapp.ui.homescreen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.R
import com.example.newsapp.ui.base.NavigationItem

enum class HomePagerTabs(
    override val route: String,
    @StringRes override val title: Int,
    override val selectedIcon: ImageVector,
    override val unselectedIcon: ImageVector
) : NavigationItem 
{
    TOP_HEADLINES(
        route = "top",
        title = R.string.top,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    SPORTS(
        route = "sports",
        title = R.string.sports,
        selectedIcon = Icons.Filled.SportsSoccer,
        unselectedIcon = Icons.Outlined.SportsSoccer
    ),
    ENTERTAINMENT(
        route = "entertainment",
        title = R.string.entertainment,
        selectedIcon = Icons.Filled.Movie,
        unselectedIcon = Icons.Outlined.Movie
    ),
    HEALTH(
        route = "health",
        title = R.string.health,
        selectedIcon = Icons.Filled.HealthAndSafety,
        unselectedIcon = Icons.Outlined.HealthAndSafety
    ),
    BUSINESS(
        route = "business",
        title = R.string.business,
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.Work
    )
}