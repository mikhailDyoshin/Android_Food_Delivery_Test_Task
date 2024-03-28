package com.example.fooddeliveryapp.domain.usecases

import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(private val repository: FoodDeliveryAppRepository) {

//    suspend fun execute(category: CategoryDomainModel): List<MealDomainModel> {
//        return repository.getMeals().filter { it.category == category.category }
//    }

}