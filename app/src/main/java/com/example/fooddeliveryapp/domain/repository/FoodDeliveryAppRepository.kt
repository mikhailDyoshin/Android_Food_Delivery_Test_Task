package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.models.MealDomainModel

interface FoodDeliveryAppRepository {

    suspend fun getMeals(): List<MealDomainModel>

    suspend fun getMealsByCategory(category: String) : List<MealDomainModel>

}