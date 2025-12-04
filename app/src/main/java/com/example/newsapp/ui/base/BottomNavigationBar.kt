package com.example.newsapp.ui.base

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun <T: NavigationItem> BottomNavigationBar(
    items: List<T>,
    currentRoute: String,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier
            .windowInsetsPadding(windowInsets.only(WindowInsetsSides.Horizontal))
            .fillMaxWidth(),
        tonalElevation = 0.dp,
        windowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0
        )
    ) {

        items.forEach { item ->
            val isSelected = item.route == currentRoute
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                label = { Text(text = stringResource(id = item.title))},
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(id = item.title)
                    )
                }
            )
        }
    }
}