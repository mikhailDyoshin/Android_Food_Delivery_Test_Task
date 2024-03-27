package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository

class FoodDeliveryAppRepositoryImpl : FoodDeliveryAppRepository {
    override suspend fun getMeals(): List<MealDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getMealsByCategory(category: String): List<MealDomainModel> {
        TODO("Not yet implemented")
    }
}