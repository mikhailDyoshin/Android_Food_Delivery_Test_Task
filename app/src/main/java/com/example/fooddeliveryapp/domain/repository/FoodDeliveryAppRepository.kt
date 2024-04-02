package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import kotlinx.coroutines.flow.Flow

interface FoodDeliveryAppRepository {

    fun getMeals(): Flow<Resource<List<MealDomainModel>>>

    fun getCategories() : Flow<Resource<List<CategoryDomainModel>>>

    fun getMealsFromDatabase(): List<MealDomainModel>

    fun getCategoriesFromDatabase() : List<CategoryDomainModel>

}