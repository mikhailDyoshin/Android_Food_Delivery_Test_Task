package com.example.fooddeliveryapp.data.storage.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealDatabaseModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val imageUrl: String
)
