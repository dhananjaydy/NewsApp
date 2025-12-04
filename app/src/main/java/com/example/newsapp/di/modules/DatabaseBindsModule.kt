package com.example.newsapp.di.modules

import com.example.newsapp.data.repository.SavedArticlesRepository
import com.example.newsapp.data.repository.SavedArticlesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseBindsModule {

    @Binds
    abstract fun bindSavedArticlesRepository(
        savedArticlesRepositoryImpl: SavedArticlesRepositoryImpl
    ): SavedArticlesRepository
}