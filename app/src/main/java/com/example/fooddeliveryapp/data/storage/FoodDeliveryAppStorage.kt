package com.example.fooddeliveryapp.data.storage

import com.example.fooddeliveryapp.data.storage.models.CategoryResponse
import com.example.fooddeliveryapp.data.storage.models.CategoryStorageModel
import com.example.fooddeliveryapp.data.storage.models.MealStorageModel
import com.example.fooddeliveryapp.data.storage.models.MealsResponse

interface FoodDeliveryAppStorage {

    suspend fun getMeals() : MealsResponse

    suspend fun getCategories() : CategoryResponse

}