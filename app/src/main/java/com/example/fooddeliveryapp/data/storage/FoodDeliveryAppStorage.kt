package com.example.fooddeliveryapp.data.storage

import com.example.fooddeliveryapp.data.storage.models.CategoryStorageModel
import com.example.fooddeliveryapp.data.storage.models.MealStorageModel

interface FoodDeliveryAppStorage {

    suspend fun getMeals() : List<MealStorageModel>

    suspend fun getCategories() : List<CategoryStorageModel>

}