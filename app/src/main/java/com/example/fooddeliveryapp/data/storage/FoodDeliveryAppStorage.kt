package com.example.fooddeliveryapp.data.storage

import com.example.fooddeliveryapp.data.storage.models.MealStorageModel

interface FoodDeliveryAppStorage {

    suspend fun getMeals() : List<MealStorageModel>

    suspend fun getMealsByCategory(category: String) : List<MealStorageModel>

}