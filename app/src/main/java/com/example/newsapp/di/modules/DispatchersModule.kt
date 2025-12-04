package com.example.newsapp.di.modules

import com.example.newsapp.dispatchers.CoroutineDispatcherProvider
import com.example.newsapp.dispatchers.ProdCoroutineDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun bindCoroutineDispatcher(
        prodCoroutineDispatcherProvider: ProdCoroutineDispatcherProvider
    ): CoroutineDispatcherProvider

}