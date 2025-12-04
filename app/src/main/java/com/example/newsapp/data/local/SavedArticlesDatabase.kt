package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SavedArticlesDatabase : RoomDatabase() {

    abstract fun savedArticlesDao(): SavedArticlesDao
}
