package com.example.fooddeliveryapp.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.storage.database.dao.CategoriesDao
import com.example.fooddeliveryapp.data.storage.database.dao.MealsDao
import com.example.fooddeliveryapp.data.storage.database.models.CategoryDatabaseModel
import com.example.fooddeliveryapp.data.storage.database.models.MealDatabaseModel

@Database(
    entities = [MealDatabaseModel::class, CategoryDatabaseModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealsDao(): MealsDao

    abstract fun categoriesDao(): CategoriesDao

}
