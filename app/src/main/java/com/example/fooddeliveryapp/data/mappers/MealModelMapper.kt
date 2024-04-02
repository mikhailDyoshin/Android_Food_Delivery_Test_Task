package com.example.fooddeliveryapp.data.mappers

import com.example.fooddeliveryapp.data.storage.database.models.MealDatabaseModel
import com.example.fooddeliveryapp.data.storage.models.MealsResponse
import com.example.fooddeliveryapp.domain.models.MealDomainModel

class MealModelMapper : ModelMapper<MealsResponse, MealDomainModel, MealDatabaseModel> {

    override fun apiToDatabase(apiResponse: MealsResponse): List<MealDatabaseModel> {
        return apiResponse.meals.mapIndexed { index, meal ->
            MealDatabaseModel(
                id = index,
                name = meal.strMeal ?: "",
                description = meal.strInstructions ?: "",
                category = meal.strCategory ?: "",
                imageUrl = meal.strMealThumb ?: ""
            )
        }
    }

    override fun apiToDomain(apiResponse: MealsResponse): List<MealDomainModel> {
        return apiResponse.meals.map { meal ->
            MealDomainModel(
                id = meal.idMeal ?: "00000",
                name = meal.strMeal ?: "No title",
                description = meal.strInstructions ?: "No description",
                category = meal.strCategory ?: "No category",
                imageUrl = meal.strMealThumb ?: "No url"
            )
        }
    }

    override fun databaseToDomain(databaseModelsList: List<MealDatabaseModel>): List<MealDomainModel> {
        return databaseModelsList.map { model ->
            MealDomainModel(
                id = model.id.toString(),
                name = model.name,
                description = model.description,
                category = model.category,
                imageUrl = model.imageUrl
            )
        }
    }

}