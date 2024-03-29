package com.example.fooddeliveryapp.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.storage.database.models.MealDatabaseModel

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(meal: MealDatabaseModel): Long

    @Query("SELECT * FROM meals")
    fun getAll() : List<MealDatabaseModel>

}