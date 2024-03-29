package com.example.fooddeliveryapp.data.repository


import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.data.mappers.CategoryModelMapper
import com.example.fooddeliveryapp.data.mappers.MealModelMapper
import com.example.fooddeliveryapp.data.mappers.ModelMapper
import com.example.fooddeliveryapp.data.storage.FoodDeliveryAppApi
import com.example.fooddeliveryapp.data.storage.database.dao.CategoriesDao
import com.example.fooddeliveryapp.data.storage.database.dao.DaoInterface
import com.example.fooddeliveryapp.data.storage.database.dao.MealsDao
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

class FoodDeliveryAppRepositoryImpl @Inject constructor(
    private val api: FoodDeliveryAppApi,
    private val mealsDao: MealsDao,
    private val categoriesDao: CategoriesDao,
) : FoodDeliveryAppRepository {
    override fun getMeals(): Flow<Resource<List<MealDomainModel>>> =
        handleResponse(
            apiCall = { api.getMeals() },
            modelMapper = MealModelMapper(),
            daoInterface = mealsDao
        )

    override fun getCategories(): Flow<Resource<List<CategoryDomainModel>>> =
        handleResponse(
            apiCall = { api.getCategories() },
            modelMapper = CategoryModelMapper(),
            daoInterface = categoriesDao
        )

    override fun getMealsFromDatabase(): List<MealDomainModel> {
        return MealModelMapper().databaseToDomain(mealsDao.getAll())
    }


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

    private fun <API, DOMAIN, DATABASE> handleResponse(
        apiCall: suspend () -> Response<API>,
        modelMapper: ModelMapper<API, DOMAIN, DATABASE>,
        daoInterface: DaoInterface<DATABASE>
    ): Flow<Resource<List<DOMAIN>>> =
        flow {
            try {
                emit(value = Resource.loading())

                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // Emitting the response
                        emit(
                            Resource.success(
                                data = modelMapper.apiToDomain(body)
                            )
                        )
                        // Caching the response to the database
                        modelMapper.apiToDatabase(body).forEach { daoInterface.insert(it) }
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
                // If there is no internet connection, emmit data stored in the database
                emit(
                    Resource.error(
                        error = Resource.Error.ERROR_NO_INTERNET_CONNECTION
                    )
                )
            }
        }


}