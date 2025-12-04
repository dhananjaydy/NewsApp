package com.example.newsapp.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.articledetailscreen.ArticleDetailScreen
import com.example.newsapp.ui.articledetailscreen.ArticleDetailViewModel
import com.example.newsapp.ui.homescreen.HomeScreen
import com.example.newsapp.ui.savedscreen.SavedScreen
import com.example.newsapp.ui.searchscreen.SearchScreen


fun NavGraphBuilder.newsAppNavGraph(
    navController: NavHostController
) {
    val onArticleClick: (Article) -> Unit = { article ->
        navController.navigate("article_detail")
        navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    }


    composable("home") {
        HomeScreen(
            onClick = {
                article -> onArticleClick(article)
            }
        )
    }
    composable("search") {
        SearchScreen(
            onClick = {
                article -> onArticleClick(article)
            }
        )
    }
    composable("saved") {
        SavedScreen(
            onClick = {
                article -> onArticleClick(article)
            }
        )
    }
    composable(
        route = "article_detail",
    ) { navBackStackEntry ->

        val viewModel: ArticleDetailViewModel = hiltViewModel(navBackStackEntry)
        val article = navBackStackEntry.savedStateHandle.get<Article>("article")
        ArticleDetailScreen(
            article = article,
            onNavigateBack = { navController.popBackStack() },
            viewModel = viewModel
        )
    }
}
