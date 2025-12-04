package com.example.newsapp.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.newsapp.BuildConfig // Import necessary for accessing the generated class
import com.example.newsapp.di.qualifiers.ApiKey

@Module
@InstallIn(SingletonComponent::class)
object NetworkConstantsModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String {
        return BuildConfig.NEWS_API_KEY
    }
}