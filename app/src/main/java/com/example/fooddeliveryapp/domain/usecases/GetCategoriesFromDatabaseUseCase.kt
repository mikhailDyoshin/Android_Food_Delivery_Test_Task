package com.example.fooddeliveryapp.domain.usecases

import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesFromDatabaseUseCase @Inject constructor(private val repository: FoodDeliveryAppRepository) {

    fun execute():List<CategoryDomainModel> {
        return repository.getCategoriesFromDatabase()
    }

}
