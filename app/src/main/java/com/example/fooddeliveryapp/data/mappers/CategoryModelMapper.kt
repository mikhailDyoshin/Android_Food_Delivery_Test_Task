package com.example.fooddeliveryapp.data.mappers

import com.example.fooddeliveryapp.data.storage.database.models.CategoryDatabaseModel
import com.example.fooddeliveryapp.data.storage.models.CategoryResponse
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel

class CategoryModelMapper : ModelMapper<CategoryResponse, CategoryDomainModel, CategoryDatabaseModel> {

    override fun apiToDatabase(apiResponse: CategoryResponse): List<CategoryDatabaseModel> {
        return apiResponse.categories.mapIndexed { index, meal ->
            CategoryDatabaseModel(
                id = index,
                category = meal.strCategory ?: "",
            )
        }
    }

    override fun apiToDomain(apiResponse: CategoryResponse): List<CategoryDomainModel> {
        return apiResponse.categories.map { meal ->
            CategoryDomainModel(
                category = meal.strCategory ?: "No category",
            )
        }
    }

    override fun databaseToDomain(databaseModelsList: List<CategoryDatabaseModel>): List<CategoryDomainModel> {
        return databaseModelsList.map { model ->
            CategoryDomainModel(
                category = model.category,
            )
        }
    }
}