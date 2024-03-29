package com.example.fooddeliveryapp.data.storage.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String
)
