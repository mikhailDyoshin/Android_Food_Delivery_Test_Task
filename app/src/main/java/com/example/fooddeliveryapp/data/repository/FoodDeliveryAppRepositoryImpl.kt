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
                id = it.idMeal ?: "00000",
                name = it.strMeal ?: "No title",
                description = it.strInstructions ?: "No description",
                category = it.strCategory ?: "No category",
                imageUrl = it.strMealThumb ?: "No url"
            )
        }
    }

    override suspend fun getCategories(): List<CategoryDomainModel> {
        return api.getCategories().categories.map {
            CategoryDomainModel(category = it.strCategory ?: "No category")
        }
    }
}