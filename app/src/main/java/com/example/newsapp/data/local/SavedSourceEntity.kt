package com.example.newsapp.data.local

import androidx.room.ColumnInfo

data class SavedSourceEntity(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)