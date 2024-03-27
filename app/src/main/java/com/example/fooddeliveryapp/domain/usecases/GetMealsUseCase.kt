package com.example.fooddeliveryapp.domain.usecases

import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(private val repository: FoodDeliveryAppRepository) {

    suspend fun execute(): List<MealDomainModel> {
        return repository.getMeals()
    }

}