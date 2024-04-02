package com.example.fooddeliveryapp.domain.usecases

import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(private val repository: FoodDeliveryAppRepository) {

    fun execute(category: CategoryDomainModel): Flow<Resource<List<MealDomainModel>>> {
        return repository.getMeals()
    }

}