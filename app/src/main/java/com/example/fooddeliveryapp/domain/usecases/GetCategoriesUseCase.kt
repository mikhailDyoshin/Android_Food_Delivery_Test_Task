package com.example.fooddeliveryapp.domain.usecases

import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: FoodDeliveryAppRepository) {

    suspend fun execute(): List<CategoryDomainModel> {
        return repository.getCategories()
    }

}