package com.example.newsapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.base.BottomNavigationBar
import com.example.newsapp.ui.base.NewsBottomTabs
import com.example.newsapp.ui.newsAppNavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(
    modifier: Modifier = Modifier,
) {

    val navController: NavHostController = rememberNavController()
    val items = NewsBottomTabs.entries
    val showOnBottomItems = items.filter { it.showBottomBar }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentTab = items.find { it.route == currentRoute } ?: NewsBottomTabs.Home

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (showOnBottomItems.contains(currentTab)) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = currentTab.title))
                    }
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                items = showOnBottomItems,
                currentRoute = currentRoute ?: NewsBottomTabs.Home.route,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NewsBottomTabs.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            newsAppNavGraph(navController = navController)
        }

    }
}

