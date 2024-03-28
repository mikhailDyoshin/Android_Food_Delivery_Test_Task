package com.example.fooddeliveryapp.data.storage

import com.example.fooddeliveryapp.data.storage.models.CategoryResponse
import com.example.fooddeliveryapp.data.storage.models.CategoryStorageModel
import com.example.fooddeliveryapp.data.storage.models.MealStorageModel
import com.example.fooddeliveryapp.data.storage.models.MealsResponse
import retrofit2.Response

interface FoodDeliveryAppStorage {

    suspend fun getMeals() : Response<MealsResponse>

    suspend fun getCategories() : Response<CategoryResponse>

}