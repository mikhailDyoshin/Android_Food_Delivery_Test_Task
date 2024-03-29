package com.example.fooddeliveryapp.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.storage.database.models.CategoryDatabaseModel

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: CategoryDatabaseModel): Long

    @Query("SELECT * FROM categories")
    fun getAll() : List<CategoryDatabaseModel>

}