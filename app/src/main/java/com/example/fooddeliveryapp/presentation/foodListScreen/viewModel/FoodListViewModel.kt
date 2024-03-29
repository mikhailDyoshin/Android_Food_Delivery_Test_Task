package com.example.fooddeliveryapp.presentation.foodListScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.domain.usecases.GetCategoriesUseCase
import com.example.fooddeliveryapp.domain.usecases.GetMealsFromDatabaseUseCase
import com.example.fooddeliveryapp.domain.usecases.GetMealsUseCase
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
) :
    ViewModel() {

    private val _mealsListState = mutableStateOf(Resource.success(data = listOf<MealState>()))
    private val _categoriesListState = mutableStateOf(Resource.success(data = listOf<CategoryState>()))

    val mealsListState: State<Resource<List<MealState>>> = _mealsListState
    val categoriesListState: State<Resource<List<CategoryState>>> = _categoriesListState

    init {
//        getCategories()
        getMeals()
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
                                    data = data.map {
                                        MealState(
                                            title = it.name,
                                            description = it.description
                                        )
                                    })

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
                        data = emptyList()
                    )
                }

                Resource.Status.SUCCESS -> {
                    if (result.data == null) {
                        _categoriesListState.value = Resource.error(
                            error = Resource.Error.ERROR_NO_DATA,
                            data = emptyList()
                        )
                    } else {
                        val data = result.data
                        _categoriesListState.value = Resource.success(
                            data = data.map {
                                CategoryState(
                                    category = it.category,
                                )
                            })

                    }
                }

                Resource.Status.ERROR -> {
                    val error = result.error ?: Resource.Error.ERROR_UNDEFINED

                    _categoriesListState.value = Resource.error(
                        error = error,
                        data = emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMealsFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
           val listOfMeals = getMealsFromDatabaseUseCase.execute()
            _mealsListState.value = Resource.success(
                data = listOfMeals.map {
                    MealState(
                        title = it.name,
                        description = it.description
                    )
                })
        }

    }
}