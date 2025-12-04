package com.example.newsapp.data.paging

sealed class NewsRequestType {
    data object TopHeadlines : NewsRequestType()
    data class Search(val query: String) : NewsRequestType()
    data class Category(val categoryName: String) : NewsRequestType()
}