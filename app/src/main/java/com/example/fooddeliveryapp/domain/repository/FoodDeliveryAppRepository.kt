package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel

interface FoodDeliveryAppRepository {

    suspend fun getMeals(): List<MealDomainModel>

    suspend fun getCategories() : List<CategoryDomainModel>

}