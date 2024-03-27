package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.storage.FoodDeliveryAppApi
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import javax.inject.Inject

class FoodDeliveryAppRepositoryImpl @Inject constructor(private val api: FoodDeliveryAppApi) :
    FoodDeliveryAppRepository {
    override suspend fun getMeals(): List<MealDomainModel> {
        return api.getMeals().meals.map {
            MealDomainModel(
                id = it.idMeal,
                name = it.strMeal,
                description = it.strInstructions,
                category = it.strCategory,
                imageUrl = it.strMealThumb
            )
        }
    }

    override suspend fun getCategories(): List<CategoryDomainModel> {
        return api.getCategories().categories.map {
            CategoryDomainModel(category = it.strCategory)
        }
    }
}