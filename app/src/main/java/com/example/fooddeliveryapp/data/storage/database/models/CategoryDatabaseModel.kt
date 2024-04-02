package com.example.fooddeliveryapp.data.storage.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDatabaseModel(
    @PrimaryKey
    val id: Int,
    val category: String
)
