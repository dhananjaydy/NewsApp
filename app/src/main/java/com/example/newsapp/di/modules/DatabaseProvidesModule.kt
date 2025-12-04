package com.example.newsapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.SavedArticlesDatabase // Your abstract database class
import com.example.newsapp.data.local.SavedArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvidesModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): SavedArticlesDatabase {
        return Room.databaseBuilder(
            context,
            SavedArticlesDatabase::class.java,
            "saved_articles_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideSavedArticlesDao(database: SavedArticlesDatabase): SavedArticlesDao {
        return database.savedArticlesDao()
    }
}
