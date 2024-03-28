package com.example.fooddeliveryapp.data.repository

import android.util.Log
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.data.storage.FoodDeliveryAppApi
import com.example.fooddeliveryapp.data.storage.models.CategoryResponse
import com.example.fooddeliveryapp.data.storage.models.MealsResponse
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class FoodDeliveryAppRepositoryImpl @Inject constructor(private val api: FoodDeliveryAppApi) :
    FoodDeliveryAppRepository {
    override fun getMeals(): Flow<Resource<List<MealDomainModel>>> =
        handleResponse(apiCall = { api.getMeals() }, modelMapper = { mealMapper(it) })

    override fun getCategories(): Flow<Resource<List<CategoryDomainModel>>> =
        handleResponse(apiCall = { api.getCategories() }, modelMapper = { categoryMapper(it) })

    private fun mealMapper(mealsResponse: MealsResponse): List<MealDomainModel> {
        val mealsList = mealsResponse.meals
        return mealsList.map { meal ->
            MealDomainModel(
                id = meal.idMeal ?: "00000",
                name = meal.strMeal ?: "No title",
                description = meal.strInstructions ?: "No description",
                category = meal.strCategory ?: "No category",
                imageUrl = meal.strMealThumb ?: "No url"
            )
        }
    }

    private fun categoryMapper(categoryResponse: CategoryResponse): List<CategoryDomainModel> {
        val categoriesList = categoryResponse.categories
        return categoriesList.map { category ->
            CategoryDomainModel(
                category = category.strCategory ?: "No category"
            )
        }
    }

    private fun <T, R> handleResponse(
        apiCall: suspend () -> Response<T>,
        modelMapper: (T) -> R
    ): Flow<Resource<R>> =
        flow {
            try {
                emit(value = Resource.loading())

                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(
                            Resource.success(
                                data = modelMapper(body)
                            )
                        )
                    }
                } else {
                    val errorMessage = response.raw()
                    when (response.code()) {
                        401 -> emit(Resource.error(error = Resource.Error.ERROR_401))
                        403 -> {
                            emit(Resource.error(error = Resource.Error.ERROR_403))
                        }

                        404 -> emit(Resource.error(error = Resource.Error.ERROR_404))
                        500 -> emit(Resource.error(error = Resource.Error.ERROR_500))
                        else -> emit(Resource.error(error = Resource.Error.ERROR_UNDEFINED))
                    }
                }
            } catch (e: IOException) {
                emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
            }
        }
}