package com.example.fooddeliveryapp.presentation.foodListScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.domain.models.CategoryDomainModel
import com.example.fooddeliveryapp.domain.models.MealDomainModel
import com.example.fooddeliveryapp.domain.usecases.GetCategoriesFromDatabaseUseCase
import com.example.fooddeliveryapp.domain.usecases.GetCategoriesUseCase
import com.example.fooddeliveryapp.domain.usecases.GetMealsFromDatabaseUseCase
import com.example.fooddeliveryapp.domain.usecases.GetMealsUseCase
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoriesListState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMealsFromDatabaseUseCase: GetMealsFromDatabaseUseCase,
    private val getCategoriesFromDatabaseUseCase: GetCategoriesFromDatabaseUseCase
) :
    ViewModel() {

    private val _mealsListState = mutableStateOf(Resource.success(data = listOf<MealState>()))
    private val _categoriesListState =
        mutableStateOf(Resource.success(data = CategoriesListState()))

    val mealsListState: State<Resource<List<MealState>>> = _mealsListState
    val categoriesListState: State<Resource<CategoriesListState>> = _categoriesListState

    init {
        getCategories()
        getMeals()
    }

    fun filterMeals(category: CategoryState) {
        getMealsUseCase.execute().onEach { result ->
            when (result.status) {
                Resource.Status.LOADING -> {
                    selectCategory(category = category)
                    _mealsListState.value = Resource.loading(
                        data = emptyList()
                    )
                }

                Resource.Status.SUCCESS -> {
                    if (result.data == null) {
                        selectCategory(category = category)
                        _mealsListState.value = Resource.error(
                            error = Resource.Error.ERROR_NO_DATA,
                            data = emptyList()
                        )
                    } else {
                        selectCategory(category = category)
                        val data = result.data.filter { it.category == category.category }
                        _mealsListState.value = Resource.success(
                            data = mealModelMapper(data)
                        )

                    }
                }

                Resource.Status.ERROR -> {

                    if (result.error == Resource.Error.ERROR_NO_INTERNET_CONNECTION) {
                        getFilteredMealsFromDatabase(category = category)
                    } else {
                        selectCategory(category = category)
                        val error = result.error ?: Resource.Error.ERROR_UNDEFINED

                        _mealsListState.value = Resource.error(
                            error = error,
                            data = emptyList()
                        )
                    }


                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getMeals() {
        getMealsUseCase.execute().onEach { result ->
            when (result.status) {
                Resource.Status.LOADING -> {
                    _mealsListState.value = Resource.loading(
                        data = emptyList()
                    )
                }

                Resource.Status.SUCCESS -> {
                    if (result.data == null) {
                        _mealsListState.value = Resource.error(
                            error = Resource.Error.ERROR_NO_DATA,
                            data = emptyList()
                        )
                    } else {
                        val data = result.data
                        _mealsListState.value = Resource.success(
                            data = mealModelMapper(data)
                        )

                    }
                }

                Resource.Status.ERROR -> {

                    if (result.error == Resource.Error.ERROR_NO_INTERNET_CONNECTION) {
                        getMealsFromDatabase()
                    } else {
                        val error = result.error ?: Resource.Error.ERROR_UNDEFINED

                        _mealsListState.value = Resource.error(
                            error = error,
                            data = emptyList()
                        )
                    }


                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        getCategoriesUseCase.execute().onEach { result ->
            when (result.status) {
                Resource.Status.LOADING -> {
                    _categoriesListState.value = Resource.loading(
                        data = CategoriesListState()
                    )
                }

                Resource.Status.SUCCESS -> {
                    if (result.data == null) {
                        _categoriesListState.value = Resource.error(
                            error = Resource.Error.ERROR_NO_DATA,
                            data = CategoriesListState()
                        )
                    } else {
                        val data = result.data
                        val categoriesList = categoryModelMapper(data)
                        _categoriesListState.value = Resource.success(
                            data = CategoriesListState(categoriesList = categoriesList)
                        )

                    }
                }

                Resource.Status.ERROR -> {
                    if (result.error == Resource.Error.ERROR_NO_INTERNET_CONNECTION) {
                        getCategoriesFromDatabase()
                    } else {
                        val error = result.error ?: Resource.Error.ERROR_UNDEFINED

                        _categoriesListState.value = Resource.error(
                            error = error,
                            data = CategoriesListState()
                        )
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMealsFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfMeals = getMealsFromDatabaseUseCase.execute()
            _mealsListState.value = Resource.success(
                data = mealModelMapper(listOfMeals)
            )
        }
    }

    private fun getCategoriesFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfCategories = getCategoriesFromDatabaseUseCase.execute()
            val categoriesListState =
                CategoriesListState(categoriesList = categoryModelMapper(listOfCategories))
            _categoriesListState.value = Resource.success(
                data = categoriesListState
            )
        }
    }

    private fun getFilteredMealsFromDatabase(category: CategoryState) {
        viewModelScope.launch(Dispatchers.IO) {
            selectCategory(category = category)
            val listOfMeals =
                getMealsFromDatabaseUseCase.execute().filter { it.category == category.category }
            _mealsListState.value = Resource.success(
                data = mealModelMapper(listOfMeals)
            )
        }
    }

    private fun selectCategory(category: CategoryState) {
        val categoriesList = _categoriesListState.value.data?.categoriesList
        val selectedCategoryIndex = categoriesList?.indexOf(category)

        val newCategoriesList = mutableListOf<CategoryState>()
        if (selectedCategoryIndex != null && selectedCategoryIndex >= 0) {
            categoriesList.forEach {
                newCategoriesList.add(
                    CategoryState(
                        category = it.category,
                        selected = categoriesList.indexOf(it) == selectedCategoryIndex
                    )
                )
            }

            val newData = _categoriesListState.value.data?.copy(
                categoriesList = newCategoriesList,
                selectedCategoryIndex = selectedCategoryIndex
            )

            _categoriesListState.value = _categoriesListState.value.copy(data = newData)
        }
    }

    private fun mealModelMapper(mealsList: List<MealDomainModel>): List<MealState> {
        return mealsList.map {
            MealState(
                title = it.name,
                description = it.description,
                imageUrl = it.imageUrl
            )
        }
    }

    private fun categoryModelMapper(categoriesList: List<CategoryDomainModel>): List<CategoryState> {
        return categoriesList.map {
            CategoryState(
                category = it.category
            )
        }
    }
}