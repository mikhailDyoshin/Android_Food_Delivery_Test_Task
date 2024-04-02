package com.example.fooddeliveryapp.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.storage.database.models.CategoryDatabaseModel

@Dao
interface CategoriesDao : DaoInterface<CategoryDatabaseModel> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(model: CategoryDatabaseModel): Long

    @Query("SELECT * FROM categories")
    override fun getAll() : List<CategoryDatabaseModel>

}