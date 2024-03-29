package com.example.fooddeliveryapp.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.storage.database.models.MealDatabaseModel

@Dao
interface MealsDao : DaoInterface<MealDatabaseModel> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    override suspend fun insert(model: MealDatabaseModel): Long

    @Query("SELECT * FROM meals")
    override fun getAll() : List<MealDatabaseModel>

}